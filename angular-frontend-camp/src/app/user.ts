
export class User {
//     id: number;
//     name: string;
    forgotPasswordToken :String;
    userName :String;

    constructor(forgotPasswordToken:String,userName:String){
//         this.id = id;
//         this.name = name;
        this.forgotPasswordToken=forgotPasswordToken;
        this.userName=userName;

    }

}
