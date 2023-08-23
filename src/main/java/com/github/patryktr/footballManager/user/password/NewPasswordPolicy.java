package com.github.patryktr.footballManager.user.password;

import com.github.patryktr.footballManager.common.ValidationResult;

public interface NewPasswordPolicy {
    ValidationResult validate(String password);
}
