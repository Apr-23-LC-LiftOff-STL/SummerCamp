
export class Camp {
    id: number;
    name: string;
    description: string;
    price: number;
    duration: string;
    age: number;
    deadline: Date;
    total_seats: number;
    mode: string;
    category: string;
    campLink: string;
    
    

    constructor(id: number, name: string, description: string, price: number, duration: string, age: number,
        deadline: Date, total_seats: number, mode: string, category: string, camp_link: string){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.age = age;
        this.deadline = deadline;
        this.total_seats = total_seats;
        this.mode = mode;
        this.category = category;
        this.campLink = camp_link;
    }


    
}
