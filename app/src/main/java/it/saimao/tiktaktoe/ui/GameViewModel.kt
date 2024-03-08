package it.saimao.tiktaktoe.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var state by mutableStateOf(GameState())

    val boardItems: MutableMap<Int, BoardCellValue> = mutableMapOf(
        1 to BoardCellValue.None,
        2 to BoardCellValue.None,
        3 to BoardCellValue.None,
        4 to BoardCellValue.None,
        5 to BoardCellValue.None,
        6 to BoardCellValue.None,
        7 to BoardCellValue.None,
        8 to BoardCellValue.None,
        9 to BoardCellValue.None,
    )

    fun onAction(actions: UserActions) {
        when (actions) {
            is UserActions.BoardTapped -> {
                addValueToBoard(actions.cellNo)
            }

            UserActions.PlayAgainButtonClicked -> {
                boardItems.forEach { (i, _) ->
                    boardItems[i] = BoardCellValue.None
                }
                state = state.copy(
                    currentTurn = BoardCellValue.Circle,
                    hintText = "Player 'O' turn",
                    victoryType = VictoryType.None,
                    hasWon = false
                )
            }
        }
    }

    private fun addValueToBoard(cellNo: Int) {
        if (boardItems[cellNo] != BoardCellValue.None) return
        if (state.currentTurn == BoardCellValue.Circle) {
            boardItems[cellNo] = BoardCellValue.Circle

            if (checkForVictory(state.currentTurn)) {
                state = state.copy(
                    hintText = "Player 'O' WIN!",
                    playerCircleCount = state.playerCircleCount + 1,
                    currentTurn = BoardCellValue.None,
                    hasWon = true
                )
                return
            }


            state = if (hasBoardFull()) {
                state.copy(
                    hintText = "Game Draw!",
                    drawCount = state.drawCount + 1
                )
            } else {
                state.copy(
                    hintText = "Player 'X' turn",
                    currentTurn = BoardCellValue.Cross,
                )
            }
        } else {
            boardItems[cellNo] = BoardCellValue.Cross
            if (checkForVictory(state.currentTurn)) {
                state = state.copy(
                    hintText = "Player 'X' WIN!",
                    playerCrossCount = state.playerCrossCount + 1,
                    currentTurn = BoardCellValue.None,
                    hasWon = true
                )
                return
            }

            state = if (hasBoardFull()) {
                state.copy(
                    hintText = "Game Draw!",
                    drawCount = state.drawCount + 1
                )
            } else {
                state.copy(
                    hintText = "Player 'O' turn",
                    currentTurn = BoardCellValue.Circle,
                )
            }
        }
    }

    private fun checkForVictory(boardValue: BoardCellValue): Boolean {
        when {
            boardItems[1] == boardValue && boardItems[2] == boardValue && boardItems[3] == boardValue -> {
                state = state.copy(victoryType = VictoryType.Row1)
                return true
            }

            boardItems[4] == boardValue && boardItems[5] == boardValue && boardItems[6] == boardValue -> {
                state = state.copy(victoryType = VictoryType.Row2)
                return true
            }

            boardItems[7] == boardValue && boardItems[8] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(victoryType = VictoryType.Row3)
                return true
            }

            boardItems[1] == boardValue && boardItems[4] == boardValue && boardItems[7] == boardValue -> {
                state = state.copy(victoryType = VictoryType.Colum1)
                return true
            }

            boardItems[2] == boardValue && boardItems[5] == boardValue && boardItems[8] == boardValue -> {
                state = state.copy(victoryType = VictoryType.Colum2)
                return true
            }

            boardItems[3] == boardValue && boardItems[6] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(victoryType = VictoryType.Colum3)
                return true
            }

            boardItems[1] == boardValue && boardItems[5] == boardValue && boardItems[9] == boardValue -> {
                state = state.copy(victoryType = VictoryType.Cross1)
                return true
            }

            boardItems[3] == boardValue && boardItems[5] == boardValue && boardItems[7] == boardValue -> {
                state = state.copy(victoryType = VictoryType.Cross2)
                return true
            }

            else -> return false

        }
    }


    private fun hasBoardFull(): Boolean {
        return boardItems.all { it.value != BoardCellValue.None }
    }

}