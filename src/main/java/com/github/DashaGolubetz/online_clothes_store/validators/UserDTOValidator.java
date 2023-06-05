package com.github.DashaGolubetz.online_clothes_store.validators;

import com.github.DashaGolubetz.online_clothes_store.dtos.UserDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Класс, который проверяет целостность и корректность введённых данных в поля класса {@link UserDTO}.
 */
@Component
public class UserDTOValidator implements Validator {
    /**
     * Функция, проверяющая, поддерживается ли валидация передаваемого объекта.
     *
     * @param clazz {@link Class}<?> (любой класс).</?>
     * @return {@link Boolean} (true, если класс поддерживается, false - если нет).
     */
    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    /**
     * Функция, валидирующая поля класса объекта.
     *
     * @param target {@link Object} (объект).
     * @param errors {@link Errors} (ошибки).
     */
    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        UserDTO userDTO = (UserDTO) target;

        if (!userDTO.getPassword().equals(userDTO.getConfirmedPassword()))
            errors.rejectValue("confirmedPassword", "", "Пароли должны совпадать.");
    }
}
