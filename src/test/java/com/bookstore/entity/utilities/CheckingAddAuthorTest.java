package com.bookstore.entity.utilities;

import com.bookstore.entity.Author;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.expectThrows;

public class CheckingAddAuthorTest {

static int count = 0;

    @DataProvider(name = "authorData")
    public Object[][] createAuthorData() {
        return new Object[][]{
                {null, "lastName", "patronymic", "Братюнь имя автора не может быть пустым или null. Сделай нормально"},
                {"", "lastName", "patronymic", "Братюнь имя автора не может быть пустым или null. Сделай нормально"},
                {"    ", "lastName", "patronymic", "Братюнь имя автора не может быть пустым или состоять " +
                        "только из символов и пробелов. Сделай нормально"},
                {"!@#$#%^#", "lastName", "patronymic", "Братюнь имя автора не может быть пустым или состоять " +
                        "только из символов и пробелов. Сделай нормально"},

                {"name", null, "patronymic", "Братюнь фамилия автора не может быть пустой или null. Сделай нормально"},
                {"name", "", "patronymic", "Братюнь фамилия автора не может быть пустой или null. Сделай нормально"},
                {"name", "     ", "patronymic", "Братюнь фамилия автора не может быть пустой или состоять " +
                        "только из символов и пробелов. Сделай нормально"},
                {"name", "@!*@*$&@#", "patronymic", "Братюнь фамилия автора не может быть пустой или состоять " +
                        "только из символов и пробелов. Сделай нормально"},

                {"name", "lastName", "   ", "Братюнь отчество автора не может состоять " +
                        "только из символов и пробелов. Сделай нормально"},
                {"name", "lastName", "@@$$#%^$#", "Братюнь отчество автора не может состоять " +
                        "только из символов и пробелов. Сделай нормально"}
        };
    }

    @Test(dataProvider = "authorData")
    public void checkingTransmittedArgumentsForAuthor(String name, String lastName, String patronymic
            , String expectedMassage) {
        count++;
        System.out.printf("Тест №%d начал работу", count);
        Author author = new Author();
        author.setAuthorName(name);
        author.setAuthorLastName(lastName);
        author.setAuthorPatronymic(patronymic);

        IllegalArgumentException exception = expectThrows(IllegalArgumentException.class, () ->
                CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(author));
        assertEquals(expectedMassage, exception.getMessage());
    }


    @Test
    void checkingTransmittedArgumentsForAuthor_NameLetterCase(){
        System.out.println("И вот еще тест checkingTransmittedArgumentsForAuthor_NameLetterCase");
        Author author1 = new Author();
        author1.setAuthorName("iVan");
        author1.setAuthorLastName("ivANOV");
        author1.setAuthorPatronymic("iVAnovich");

        Author result = CheckingAddAuthor.checkingTransmittedArgumentsForAuthor(author1);

        assertEquals("Ivan",result.getAuthorName());
        assertEquals("Ivanov",result.getAuthorLastName());
        assertEquals("Ivanovich",result.getAuthorPatronymic());
    }


}
