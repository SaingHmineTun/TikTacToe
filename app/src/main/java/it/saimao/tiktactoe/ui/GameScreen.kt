package it.saimao.tiktactoe.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import it.saimao.tiktactoe.ui.theme.BlueCustom
import it.saimao.tiktactoe.ui.theme.GrayBackground

@Composable
fun GameScreen(
    viewModel: GameViewModel = viewModel()
) {
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Player 'O': ${state.playerCircleCount}", fontSize = 16.sp)
            Text(text = "Draw: ${state.drawCount}", fontSize = 16.sp)
            Text(text = "Player 'X': ${state.playerCrossCount}", fontSize = 16.sp)
        }
        Text(
            text = "Tik Tac Toe",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = BlueCustom
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1F)
                .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
                .clip(
                    RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            BoardBase()
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                    .aspectRatio(1F),
                content = {
                    viewModel.boardItems.forEach { (cellNo, boardCell) ->
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null
                                    ) {
                                        if (!state.hasWon)
                                            viewModel.onAction(UserActions.BoardTapped(cellNo))
                                    },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AnimatedVisibility(
                                    visible = viewModel.boardItems[cellNo] != BoardCellValue.None,
                                    enter = scaleIn(
                                        tween()
                                    )
                                ) {
                                    if (boardCell == BoardCellValue.Circle) {
                                        Circle()
                                    } else if (boardCell == BoardCellValue.Cross) {
                                        Cross()
                                    }
                                }
                            }
                        }
                    }
                })

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(visible = state.hasWon, enter = fadeIn(tween())) {
                    VictoryLine(state.victoryType)
                }
            }
        }
        BottomLayout(state.hintText) {
            viewModel.onAction(UserActions.PlayAgainButtonClicked)
        }
    }
}

@Composable
fun BottomLayout(hintText: String, onPlayAgain: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = hintText, fontSize = 18.sp, fontStyle = FontStyle.Italic)
        Button(
            onClick = onPlayAgain,
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueCustom
            ),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(text = "Play Again")
        }
    }
}

@Composable
fun VictoryLine(victoryType: VictoryType) {
    when (victoryType) {
        VictoryType.Row1 -> WinningLineRow1()
        VictoryType.Row2 -> WinningLineRow2()
        VictoryType.Row3 -> WinningLineRow3()
        VictoryType.Colum1 -> WinningLineCol1()
        VictoryType.Colum2 -> WinningLineCol2()
        VictoryType.Colum3 -> WinningLineCol3()
        VictoryType.Cross1 -> WinningLineCross1()
        VictoryType.Cross2 -> WinningLineCross2()
        else -> {}
    }

}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen()
}