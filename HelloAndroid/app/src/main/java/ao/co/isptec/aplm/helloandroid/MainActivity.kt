
package ao.co.isptec.aplm.helloandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ao.co.isptec.aplm.helloandroid.ui.theme.HelloAndroidTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("MainActivity", "Aplicação iniciada com sucesso.")

        enableEdgeToEdge()

        setContent {
            HelloAndroidTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {

    val context = LocalContext.current

    var text by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Escreve uma mensagem") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

                Toast.makeText(
                    context,
                    "Botão clicado!",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d("MainActivity", "Mensagem digitada: $text")

                val intent = Intent(
                    context,
                    DisplayMessageActivity::class.java
                ).apply {
                    putExtra("EXTRA_MESSAGE", text)
                }

                context.startActivity(intent)
            }
        ) {
            Text("Enviar")
        }
    }
}
