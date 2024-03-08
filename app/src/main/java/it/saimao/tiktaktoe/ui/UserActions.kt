package it.saimao.tiktaktoe.ui

sealed class UserActions {
    data object PlayAgainButtonClicked: UserActions()
    data class BoardTapped(val cellNo: Int): UserActions()

}