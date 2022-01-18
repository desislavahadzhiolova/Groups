package ia.wit.groups_desislavahad

//not using a data class because of firebase - needs an empty constructor
class User {
    var name: String? = null
    var email: String? = null
    var uid: String? = null

    constructor(){}
    constructor(name: String?, email: String, uid: String){
        this.name = name
        this.email = email
        this.uid = uid
    }


}