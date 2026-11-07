
package ao.co.isptec.aplm.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import ao.co.isptec.aplm.helloandroid.ui.theme.HelloAndroidTheme

class DisplayMessageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val message = intent.getStringExtra("EXTRA_MESSAGE")
            ?: "Nenhuma mensagem recebida."

        setContent {
            HelloAndroidTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = message,
                        fontSize = 24.sp
                    )
                }
            }
        }
    }
}
