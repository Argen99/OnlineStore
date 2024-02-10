package com.example.onlinestore.ui.fragments.auth.sign_up

import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.onlinestore.R
import com.example.onlinestore.core.base.BaseFragment
import com.example.onlinestore.core.extensions.activityNavController
import com.example.onlinestore.core.extensions.isValidAndNotEmpty
import com.example.onlinestore.core.extensions.isValidOrEmpty
import com.example.onlinestore.core.extensions.navigateSafely
import com.example.onlinestore.core.utils.Object.KEY_IS_AUTHORIZED
import com.example.onlinestore.core.utils.Object.MAX_PHONE_NUMBER_LENGTH
import com.example.onlinestore.core.utils.PhoneNumberTextWatcher
import com.example.onlinestore.databinding.FragmentSignUpBinding
import com.example.onlinestore.ui.model.UserDataUI
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {

    override val binding by viewBinding(FragmentSignUpBinding::bind)
    override val viewModel by viewModel<SignUpViewModel>()

    override fun setupListeners() {
        textChangeListeners()
        focusChangeListeners()
        binding.btnLogin.setOnClickListener {
            val data = UserDataUI(
                binding.etUserName.text.toString(),
                binding.etUserSurname.text.toString(),
                binding.etUserPhone.text.toString()
            )

            activityNavController().navigateSafely(
                R.id.action_global_mainFlowFragment,
                bundleOf(KEY_IS_AUTHORIZED to viewModel.isAuthorized(data))
            )
        }
    }

    private fun textChangeListeners() = with(binding) {
        etUserName.addTextChangedListener { editable ->
            /** isSelected используется для установки состояния view (как setError)
             * наверное не правильно так делать, но для меня было так удобнее */
            tilUserName.isSelected = !editable.toString().isValidOrEmpty()
            validateData()
        }
        etUserSurname.addTextChangedListener { editable ->
            tilUserSurname.isSelected = !editable.toString().isValidOrEmpty()
            validateData()
        }
        etUserPhone.addTextChangedListener {
            validateData()
        }
    }

    private fun focusChangeListeners() = with(binding) {
        val exampleIdValidator = PhoneNumberTextWatcher(binding.etUserPhone)
        binding.etUserPhone.addTextChangedListener(exampleIdValidator)

        val listenerToSetClearIconVisibility = OnFocusChangeListener { view, isFocused ->
            if (isFocused && (view as EditText).text.isNotEmpty())
                tilUserName.isEndIconVisible = true
        }
        etUserName.onFocusChangeListener = listenerToSetClearIconVisibility
        etUserSurname.onFocusChangeListener = listenerToSetClearIconVisibility

        etUserPhone.setOnFocusChangeListener { view, isFocused ->
            (view as EditText).hint =
                if (isFocused) null
                else getString(R.string.hint_user_phone)
        }
    }

    private fun validateData() {
        val name = binding.etUserName.text.toString()
        val surname = binding.etUserSurname.text.toString()
        val phoneNumber = binding.etUserPhone.text.toString()

        binding.btnLogin.isEnabled = name.isValidAndNotEmpty() &&
                surname.isValidAndNotEmpty() &&
                phoneNumber.length == MAX_PHONE_NUMBER_LENGTH
    }
}