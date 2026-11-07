package ao.co.isptec.aplm.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ao.co.isptec.aplm.todolist.ui.theme.TodoListTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListaTarefas()
                }
            }
        }
    }
}

@Composable
fun ListaTarefas() {

    var texto by remember {
        mutableStateOf("")
    }

    val tarefas = remember {
        mutableStateListOf<Task>()
    }

    var proximoId by remember {
        mutableIntStateOf(1)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Minha Lista de Tarefas",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = texto,
            onValueChange = {
                texto = it
            },
            label = {
                Text("Escreve uma tarefa")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {

                val titulo = texto.trim()

                if (titulo.isNotEmpty()) {

                    val existe = tarefas.any {
                        it.title.equals(titulo, ignoreCase = true)
                    }

                    if (!existe) {

                        tarefas.add(
                            Task(
                                id = proximoId++,
                                title = titulo
                            )
                        )

                        texto = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adicionar tarefa")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tarefas: ${tarefas.size}",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn {

            items(
                items = tarefas,
                key = { tarefa -> tarefa.id }
            ) { tarefa ->

                LinhaTarefa(
                    tarefa = tarefa,

                    aoAlterarEstado = { concluida ->

                        val indice = tarefas.indexOfFirst {
                            it.id == tarefa.id
                        }

                        if (indice != -1) {
                            tarefas[indice] = tarefa.copy(
                                isDone = concluida
                            )
                        }
                    },

                    aoApagar = {

                        tarefas.removeAll {
                            it.id == tarefa.id
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun LinhaTarefa(
    tarefa: Task,
    aoAlterarEstado: (Boolean) -> Unit,
    aoApagar: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),

        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = tarefa.isDone,
                onCheckedChange = aoAlterarEstado
            )

            Text(
                text = tarefa.title,

                textDecoration = if (tarefa.isDone) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                }
            )
        }

        TextButton(
            onClick = aoApagar
        ) {
            Text("Apagar")
        }
    }
}