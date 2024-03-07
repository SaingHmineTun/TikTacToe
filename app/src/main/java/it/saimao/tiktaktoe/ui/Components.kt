package it.saimao.tiktaktoe.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.saimao.tiktaktoe.ui.theme.Aqua
import it.saimao.tiktaktoe.ui.theme.GreenishYellow

@Composable
fun BoardBase() {
    Canvas(modifier = Modifier
        .size(300.dp)
        .padding(10.dp), onDraw = {
        drawLine(
            Color.Gray, strokeWidth = 5f, cap = StrokeCap.Round,
            start = Offset(x = size.width * 1 / 3, y = 0f),
            end = Offset(x = size.width * 1 / 3, y = size.height)
        )
        drawLine(
            Color.Gray, strokeWidth = 5f, cap = StrokeCap.Round,
            start = Offset(x = size.width * 2 / 3, y = 0f),
            end = Offset(x = size.width * 2 / 3, y = size.height)
        )
        drawLine(
            Color.Gray, strokeWidth = 5f, cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 1 / 3),
            end = Offset(x = size.width, y = size.height * 1 / 3)
        )
        drawLine(
            Color.Gray, strokeWidth = 5f, cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 2 / 3),
            end = Offset(x = size.width, y = size.height * 2 / 3)
        )
    })

}

@Composable
fun Cross() {
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp), onDraw = {
        drawLine(
            color = GreenishYellow,
            strokeWidth = 20F,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )
        drawLine(
            color = GreenishYellow,
            strokeWidth = 20F,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f)
        )
    })
}

@Composable
fun WinningLineRow1() {
    Canvas(modifier = Modifier.size(300.dp), onDraw = {
        drawLine(
            color = Color.Red,
            strokeWidth = 20F,
            cap = StrokeCap.Round,
            start = Offset(x = 0F, y = size.height * 1 / 6),
            end = Offset(x = size.width, y = size.height * 1 / 6),
        )
    })
}

@Composable
fun WinningLineRow2() {
    Canvas(modifier = Modifier.size(300.dp), onDraw = {
        drawLine(
            color = Color.Red,
            strokeWidth = 20F,
            cap = StrokeCap.Round,
            start = Offset(x = 0F, y = size.height * 3 / 6),
            end = Offset(x = size.width, y = size.height * 3 / 6),
        )
    })
}

@Composable
fun WinningLineRow3() {
    Canvas(modifier = Modifier.size(300.dp), onDraw = {
        drawLine(
            color = Color.Red,
            strokeWidth = 20F,
            cap = StrokeCap.Round,
            start = Offset(x = 0F, y = size.height * 5 / 6),
            end = Offset(x = size.width, y = size.height * 5 / 6),
        )
    })
}

@Composable
fun WinningLineCol1() {
    Canvas(modifier = Modifier.size(300.dp), onDraw = {
        drawLine(
            color = Color.Red,
            strokeWidth = 20F,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 1 / 6, y = 0F),
            end = Offset(x = size.width * 1 / 6, y = size.height),
        )
    })
}

@Composable
fun WinningLineCol2() {
    Canvas(modifier = Modifier.size(300.dp), onDraw = {
        drawLine(
            color = Color.Red,
            strokeWidth = 20F,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 3 / 6, y = 0F),
            end = Offset(x = size.width * 3 / 6, y = size.height),
        )
    })
}

@Composable
fun WinningLineCol3() {
    Canvas(modifier = Modifier.size(300.dp), onDraw = {
        drawLine(
            color = Color.Red,
            strokeWidth = 20F,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 5 / 6, y = 0F),
            end = Offset(x = size.width * 5 / 6, y = size.height),
        )
    })
}

@Composable
fun WinningLineCross1() {
    Canvas(modifier = Modifier.size(300.dp), onDraw = {
        drawLine(
            color = Color.Red,
            strokeWidth = 20F,
            cap = StrokeCap.Round,
            start = Offset(x = 0F, y = 0F),
            end = Offset(x = size.width, y = size.height),
        )
    })
}

@Composable
fun WinningLineCross2() {
    Canvas(modifier = Modifier.size(300.dp), onDraw = {
        drawLine(
            color = Color.Red,
            strokeWidth = 20F,
            cap = StrokeCap.Round,
            start = Offset(x = 0F, y = size.height),
            end = Offset(x = size.width, y = 0F),
        )
    })
}


@Composable
fun Circle() {
    Canvas(modifier = Modifier
        .size(60.dp)
        .padding(5.dp), onDraw = {
        drawCircle(color = Aqua, style = Stroke(width = 20f))
    })
}

@Preview
@Composable
fun BoardBasePreview() {
    BoardBase()
    WinningLineRow1()
    WinningLineRow2()
    WinningLineRow3()
    WinningLineCol1()
    WinningLineCol2()
    WinningLineCol3()
    WinningLineCross1()
    WinningLineCross2()
}