package it.saimao.tiktaktoe.ui

data class GameState(
    val playerCircleCount: Int = 0,
    val playerCrossCount: Int = 0,
    val drawCount: Int = 0,
    val currentTurn: BoardCellValue = BoardCellValue.Circle,
    val hintText: String = "Player 'O' turn",
    val victoryType: VictoryType = VictoryType.None,
    val hasWon: Boolean = false
)

enum class BoardCellValue {
    Circle,
    Cross,
    None
}

enum class VictoryType {
    Row1, Row2, Row3, Colum1, Colum2, Colum3, Cross1, Cross2, None
}