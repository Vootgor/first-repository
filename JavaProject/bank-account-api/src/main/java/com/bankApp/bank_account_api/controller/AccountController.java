package com.bankApp.bank_account_api.controller;

import com.bankApp.bank_account_api.dto.AccountDTO;
import com.bankApp.bank_account_api.dto.DepositDTO;
import com.bankApp.bank_account_api.dto.TransactionDTO;
import com.bankApp.bank_account_api.dto.TransferDTO;
import com.bankApp.bank_account_api.dto.WithdrawDTO;
import com.bankApp.bank_account_api.service.AccountService;
import com.bankApp.bank_account_api.service.TransactionService;
import com.bankApp.bank_account_api.utilities.GeneralResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с банковскими счетами. Этот контроллер обрабатывает запросы, связанные с
 * депозитами, снятием средств, переводами и получением истории транзакций по счету.
 */
@RestController
@RequestMapping("/api/v1/accounts")
@Tag(name = "Accounts", description = "Operations related to bank accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @Autowired
    public AccountController(AccountService accountService, TransactionService userService) {
        this.accountService = accountService;
        this.transactionService = userService;
    }

    /**
     * Метод для внесения денег на счет.
     *
     * @param depositDTO объект с данными о депозите, включая номер счета и сумму
     * @return ResponseEntity, содержащий ответ с данными о счете после внесения средств
     */
    @Operation(summary = "Deposit money into an account",
        description = "Allows to deposit a specified amount into the account.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully deposited"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
        })
    @PutMapping("/deposit")
    public ResponseEntity<GeneralResponse<AccountDTO>> deposit(
        @Parameter(description = "Deposit data including account number and amount")
        @RequestBody DepositDTO depositDTO) {
        return ResponseEntity.status(200)
            .body(new GeneralResponse<>(accountService.deposit(depositDTO)));
    }

    /**
     * Метод для снятия денег со счета.
     *
     * @param withdrawDTO объект с данными о снятии, включая номер счета и сумму
     * @return ResponseEntity, содержащий ответ с данными о счете после снятия средств
     */
    @Operation(summary = "Withdraw money from an account",
        description = "Allows to withdraw a specified amount from the account.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully withdrawn"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
        })
    @PutMapping("/withdraw")
    public ResponseEntity<GeneralResponse<AccountDTO>> withdraw(
        @Parameter(description = "Withdrawal data including account number and amount")
        @RequestBody WithdrawDTO withdrawDTO) {
        return ResponseEntity.status(200)
            .body(new GeneralResponse<>(accountService.withdraw(withdrawDTO)));
    }

    /**
     * Метод для перевода средств с одного счета на другой.
     *
     * @param transferDTO объект с данными о переводе, включая номера счетов и сумму
     * @return ResponseEntity, содержащий ответ с данными о счете после перевода средств
     */
    @Operation(summary = "Transfer money between accounts",
        description = "Allows to transfer funds from one account to another.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully transferred"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
        })
    @PutMapping("/transfer")
    public ResponseEntity<GeneralResponse<AccountDTO>> transfer(
        @Parameter(description = "Transfer data including source and target account numbers and amount")
        @RequestBody TransferDTO transferDTO) {
        return ResponseEntity.status(200)
            .body(new GeneralResponse<>(accountService.transfer(transferDTO)));
    }

    /**
     * Метод для получения списка транзакций по идентификатору счета.
     *
     * @param accountId идентификатор счета, для которого нужно получить транзакции
     * @return ResponseEntity, содержащий ответ со списком транзакций
     */
    @Operation(summary = "Get transactions for an account",
        description = "Retrieves all transactions associated with a specific account.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved transactions"),
            @ApiResponse(responseCode = "404", description = "Account not found")
        })
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<GeneralResponse<List<TransactionDTO>>> getTransaction(
        @Parameter(description = "ID of the account to retrieve transactions for")
        @PathVariable String accountId) {
        return ResponseEntity.status(200)
            .body(new GeneralResponse<>(transactionService.getByAllTransaction(accountId)));
    }
}
