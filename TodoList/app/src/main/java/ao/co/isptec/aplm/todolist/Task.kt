package ao.co.isptec.aplm.todolist

data class Task(
    val id: Int,
    val title: String,
    val isDone: Boolean = false
)