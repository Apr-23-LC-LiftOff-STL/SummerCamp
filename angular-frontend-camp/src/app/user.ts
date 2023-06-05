
export class User {
    id: number;
    name: string;
    forgotPasswordToken :String;
    username :String;

    constructor(id: number, name: string,forgotPasswordToken:String,username:String){
        this.id = id;
        this.name = name;
        this.forgotPasswordToken=forgotPasswordToken;
        this.username=username;
        
    }
   
}
