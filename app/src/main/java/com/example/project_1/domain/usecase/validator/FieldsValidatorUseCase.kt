package com.example.project_1.domain.usecase.validator

class FieldsValidatorUseCase {
    fun validateFields(vararg fields: String): Boolean {
        return fields.all { it.isNotEmpty() }
    }
}