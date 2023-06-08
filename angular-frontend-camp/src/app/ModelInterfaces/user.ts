export interface User {
//   id: number;
  userFirstName: string;
  userLastName: string;
  userEmail: string;
  age: number;
  grade: number;
  phone: number;
  userName: string;
  password: string;
  favorites: [];
  profilePictureLink: string;
  forgotPasswordToken :String;


/*
  constructor(
    id: number,
    firstName: string,
    lastName: string,
    email: string,
    age: number,
    grade: number,
    phone: number,
    username: string,
    password: string,
    favorites: [],
    profilePictureLink: string
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.age = age;
    this.grade = grade;
    this.phone = phone;
    this.username = username;
    this.password = password;
    this.favorites = favorites;
    this.profilePictureLink = profilePictureLink;
  }*/
}
