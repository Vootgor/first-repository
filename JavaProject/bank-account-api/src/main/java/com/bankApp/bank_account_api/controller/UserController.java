package com.bankApp.bank_account_api.controller;

import com.bankApp.bank_account_api.dto.UserDTO;
import com.bankApp.bank_account_api.service.TransactionService;
import com.bankApp.bank_account_api.service.UserService;
import com.bankApp.bank_account_api.utilities.GeneralResponse;
import com.bankApp.bank_account_api.utilities.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с пользователями. Этот контроллер обрабатывает запросы, связанные с
 * созданием пользователей и получением информации о них.
 */
@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Operations related to users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Метод для создания нового пользователя.
     *
     * @param userDTO объект с данными о пользователе
     * @return ResponseEntity, содержащий ответ с данными о созданном пользователе
     */
    @Operation(summary = "Create a new user",
        description = "Creates a new user with the provided information.",
        responses = {
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid user data")
        })
    @PostMapping
    public ResponseEntity<GeneralResponse<UserDTO>> createUser(
        @Parameter(description = "User data to create a new user")
        @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201)
            .body(new GeneralResponse<>(userService.createUser(userDTO)));
    }

    /**
     * Метод для получения информации о пользователе по идентификатору.
     *
     * @param userId идентификатор пользователя, для которого нужно получить информацию
     * @return ResponseEntity, содержащий ответ с данными о пользователе
     */
    @Operation(summary = "Get user information",
        description = "Retrieves user information by user ID.",
        responses = {
            @ApiResponse(responseCode = "200", description = "User information retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
        })
    @GetMapping("/{userId}")
    public ResponseEntity<GeneralResponse<UserDTO>> getUser(
        @Parameter(description = "ID of the user to retrieve")
        @RequestParam String userId) {
        return ResponseEntity.status(200)
            .body(new GeneralResponse<>(userService.getUser(userId)));
    }
}
