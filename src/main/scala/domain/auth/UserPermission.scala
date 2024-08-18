package domain.auth

case class UserPermission(userID: Long, permissionID: UserPermission.Permission)

object UserPermission {

  type Permission = Long

  object Permission {

    val Add_Product: Long = 1
    val Add_To_Order: Long = 2
    val Get_All_Product: Long = 3
    val Get_Order: Long = 4
    val Get_Product: Long = 5
    val Remove_From_Order: Long = 6
    val Remove_Product: Long = 7
    val Update_Order: Long = 8
    val Update_Product: Long = 9
  }
}