package com.avilaa.dataconfirm

import android.os.Bundle
import androidx.navigation.NavController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.avilaa.dataconfirm.ui.theme.DataConfirmTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataConfirmTheme {
                val navController = rememberNavController()
                val viewModel: DataViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues->
                    NavHost(
                        navController = navController,
                        startDestination = "data_confirm_screen",
                        modifier = Modifier.padding(paddingValues)
                    ) {

                        composable("data_confirm_screen") { DataConfirmScreen(navController) }
                        composable("registration_screen") { RegistrationScreen(navController, viewModel) }
                        composable("question_screen") { QuestionScreen(navController, viewModel) }
                        composable("confirmation_screen") { ConfirmationScreen(navController, viewModel) }
                        composable("cadastrado_screen") { CadastradoScreen(navController) }
                    }
                }
            }
        }
    }
}

class DataViewModel : ViewModel() {
    var name = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var dateOfBirth = MutableLiveData<String>()
    var question1 = MutableLiveData<String>()
    var question2 = MutableLiveData<String>()
    var question3 = MutableLiveData<String>()
}

@Composable
fun DataConfirmScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "DataConfirm",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Seja bem vindo ao nosso App!!",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("registration_screen")},
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text("Começar")
        }
    }
}

@Composable
fun RegistrationScreen(navController: NavController, viewModel: DataViewModel) {

    val name = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val dateOfBirth = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

    val isNameError = remember { mutableStateOf(false) }
    val isEmailError = remember { mutableStateOf(false) }
    val isDateOfBirthError = remember { mutableStateOf(false) }
    val isPasswordError = remember { mutableStateOf(false) }

    IconButton(onClick = { navController.popBackStack() }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Voltar")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cadastro",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = name.value,
            onValueChange = { newName -> name.value = newName },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth(),
            isError = isNameError.value
        )
        if (isNameError.value) {
            Text(text = "Nome é obrigatório", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = { newEmail -> email.value = newEmail },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = isEmailError.value
        )
        if (isEmailError.value) {
            Text(text = "Email é obrigatório", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dateOfBirth.value,
            onValueChange = { newDateOfBirth -> dateOfBirth.value = newDateOfBirth },
            label = { Text("Data de Nascimento") },
            modifier = Modifier.fillMaxWidth(),
            isError = isDateOfBirthError.value
        )
        if (isDateOfBirthError.value) {
            Text(text = "Data de Nascimento é obrigatório", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { newPassword -> password.value = newPassword },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = isPasswordError.value
        )
        if (isPasswordError.value) {
            Text(text = "Senha é obrigatório", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(32.dp))

        fun validateForm(
            name: MutableState<String>,
            email: MutableState<String>,
            dateOfBirth: MutableState<String>,
            password: MutableState<String>
        ): Boolean {
            isNameError.value = name.value.isBlank()
            isEmailError.value = email.value.isBlank()
            isDateOfBirthError.value = dateOfBirth.value.isBlank()
            isPasswordError.value = password.value.isBlank()

            return !isNameError.value && !isEmailError.value && !isDateOfBirthError.value && !isPasswordError.value
        }


        Button(
            onClick = {
                val isValid = validateForm(name, email, dateOfBirth, password)

                if (isValid) {
                    viewModel.name.value = name.value
                    viewModel.email.value = email.value
                    viewModel.dateOfBirth.value = dateOfBirth.value
                    navController.navigate("question_screen")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text("Confirmar")
        }


        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun QuestionScreen(navController: NavController, viewModel: DataViewModel) {

    val question1 = rememberSaveable { mutableStateOf("") }
    val question2 = rememberSaveable { mutableStateOf("") }
    val question3 = rememberSaveable { mutableStateOf("") }

    val isQuestion1Error = remember { mutableStateOf(false) }
    val isQuestion2Error = remember { mutableStateOf(false) }
    val isQuestion3Error = remember { mutableStateOf(false) }

    IconButton(onClick = { navController.popBackStack() }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Voltar")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nos responda mais algumas perguntas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Quais são suas especialidades?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        TextField(
            value = question1.value,
            onValueChange = { question1.value = it },
            label = { Text("Ex: Python, Java, JavaScript") },
            modifier = Modifier.fillMaxWidth(),
            isError = isQuestion1Error.value
        )
        if (isQuestion1Error.value) {
            Text(text = "Questão obrigatória!", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "De 0 a 10, qual é o seu nível de familiaridade com Python?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        TextField(
            value = question2.value,
            onValueChange = { question2.value = it },
            label = { Text("0-10") },
            modifier = Modifier.fillMaxWidth(),
            isError = isQuestion2Error.value
        )
        if (isQuestion2Error.value) {
            Text(text = "Questão obrigatória!", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Qual é o seu objetivo profissional?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        TextField(
            value = question3.value,
            onValueChange = { question3.value = it },
            label = { Text("Escreva aqui") },
            modifier = Modifier.fillMaxWidth(),
            isError = isQuestion3Error.value
        )
        if (isQuestion3Error.value) {
            Text(text = "Questão obrigatória!", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(32.dp))

        fun validateForm(
            question1: MutableState<String>,
            question2: MutableState<String>,
            question3: MutableState<String>,
        ): Boolean {
            isQuestion1Error.value = question1.value.isBlank()
            isQuestion2Error.value = question2.value.isBlank()
            isQuestion3Error.value = question3.value.isBlank()

            return !isQuestion1Error.value && !isQuestion2Error.value && !isQuestion3Error.value
        }

        Button(
            onClick = {
                val isValid = validateForm(question1, question2, question3)

                if (isValid) {
                    viewModel.question1.value = question1.value
                    viewModel.question2.value = question2.value
                    viewModel.question3.value = question3.value
                    navController.navigate("confirmation_screen")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            )
        ) {
            Text("Confirmar")
        }
    }
}

@Composable
fun ConfirmationScreen(navController: NavController, viewModel: DataViewModel) {

    val name = viewModel.name.value ?: "Nome não encontrado"
    val email = viewModel.email.value ?: "Email não encontrado"
    val dateOfBirth = viewModel.dateOfBirth.value ?: "Nome não encontrado"
    val question1 = viewModel.question1.value ?: "Email não encontrado"
    val question2 = viewModel.question2.value ?: "Nome não encontrado"
    val question3 = viewModel.question3.value ?: "Email não encontrado"

    IconButton(onClick = { navController.popBackStack() }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Voltar")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Confirmação de Dados",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Nome: $name",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Email: $email",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Data de Nascimento: $dateOfBirth",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Pergunta 1: $question1",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Pergunta 2: $question2",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Pergunta 3: $question3",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = { navController.navigate("cadastrado_screen") },
                modifier = Modifier.weight(1f),
            ) {
                Text("Confirmar")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f),
            ) {
                Text("Editar")
            }
        }
    }
}



@Composable
fun CadastradoScreen(navController: NavController) {
    IconButton(onClick = { navController.popBackStack() }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Voltar")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cadastrado com Sucesso!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}