package com.avilaa.dataconfirm

import android.os.Bundle
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataConfirmTheme {
                val navController = rememberNavController() // Cria o NavController

                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues->
                    NavHost(
                        navController = navController,
                        startDestination = "data_confirm_screen",
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable("data_confirm_screen") { DataConfirmScreen(navController) }
                        composable("registration_screen") { RegistrationScreen(navController) }
                        composable("question_screen") { QuestionScreen(navController) }
                        composable("confirmation_screen") { ConfirmationScreen(navController) }
                        composable("cadastrado_screen") { CadastradoScreen(navController) }
                    }
                }
            }
        }
    }
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
fun RegistrationScreen(navController: NavController) {

    val name = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val dateOfBirth = rememberSaveable { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }

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
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = email.value,
        onValueChange = { newEmail -> email.value = newEmail },
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = dateOfBirth.value,
        onValueChange = { newDateOfBirth -> dateOfBirth.value = newDateOfBirth },
        label = { Text("Data de Nascimento") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = password.value,
        onValueChange = { newPassword -> password.value = newPassword },
        label = { Text("Senha") },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(32.dp))

    Button(
        onClick = { navController.navigate("question_screen?name=${name.value}&email=${email.value}&dateOfBirth=${dateOfBirth.value}")
            println("Name Question: ${name.value}")
            println("Email Question: ${email.value}")
            println("Data de Nascimento Question: ${dateOfBirth.value}")},
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
fun QuestionScreen(navController: NavController) {

    val name = rememberSaveable { mutableStateOf(navController.currentBackStackEntry?.arguments?.getString("name") ?: "") }
    val email = rememberSaveable { mutableStateOf(navController.currentBackStackEntry?.arguments?.getString("email") ?: "") }
    val dateOfBirth = rememberSaveable { mutableStateOf(navController.currentBackStackEntry?.arguments?.getString("dateOfBirth") ?: "") }

    val question1 = remember { mutableStateOf("") }
    val question2 = remember { mutableStateOf("") }
    val question3 = remember { mutableStateOf("") }

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
            onValueChange = { question1.value = it  },
            label = { Text("Ex: Python, Java, JavaScript") },
            modifier = Modifier.fillMaxWidth()
        )

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
            modifier = Modifier.fillMaxWidth()
        )

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
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("confirmation_screen?name=$name&email=$email&dateOfBirth=$dateOfBirth&question1=${question1.value}&question2=${question2.value}&question3=${question3.value}")
                println("Name Question: ${name}")
                println("Email Question: ${email}")
                println("Data de Nascimento Question: ${dateOfBirth}")
                println("Queston1 Question: ${question1.value}")
                println("Question2 Question: ${question2.value}")
                println("Question3 Question: ${question3.value}")},
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
fun ConfirmationScreen(navController: NavController) {
    val backStackEntry = navController.previousBackStackEntry?.savedStateHandle

    val name = backStackEntry?.get<String>("name") ?: "Nome não encontrado"
    val email = backStackEntry?.get<String>("email") ?: "Email não encontrado"
    val dateOfBirth = backStackEntry?.get<String>("dateOfBirth") ?: "Data de Nascimento não encontrada."
    val question1 = backStackEntry?.get<String>("question1") ?: ""
    val question2 = backStackEntry?.get<String>("question2") ?: ""
    val question3 = backStackEntry?.get<String>("question3") ?: ""

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