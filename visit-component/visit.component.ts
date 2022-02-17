import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Component({
  selector: 'app-visit',
  templateUrl: './visit.component.html',
  styleUrls: ['./visit.component.scss']
})
export class VisitComponent implements OnInit {

  name:String="";
  email:String="";
  password:String="";
  year!:String;
  month!:String;
  day!:String;
  startHour!:String;
  endHour!:String;
  startMinute!:String;
  endMinute!:String;
  visitId!:String;
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
  }

  create(){
    var reqBody={
      name:this.name,
      email:this.email,
      password:this.password,
      role:["user"]
    }
    this.http.post(environment.api+"/auth/signup",reqBody).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }
  createAdmin(){
    var reqBody={
      name:this.name,
      email:this.email,
      password:this.password,
      role:["admin"]
    }
    this.http.post(environment.api+"/auth/signup",reqBody).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }
  login(){
    var reqBody={
      email:this.email,
      password:this.password,
    }
    this.http.post(environment.api+"/auth/signin",reqBody).subscribe(
      (response:any) => {
        console.log(response)
        localStorage.setItem("token",response.token)
      },
      error => console.log(error)
    );
  }
  update(){
    var httpOptions={
      headers:new HttpHeaders({
        "Authorization" : "Bearer "+localStorage.getItem("token")
      })
    };
    var reqBody={
      year:this.year,
      month:this.month,
      day: this.day,
      startHour:this.startHour,
      endHour:this.endHour,
      startMinute:this.startMinute,
      endMinute:this.endMinute
    }
    this.http.put(environment.api+"/visit/update",reqBody,httpOptions).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }
  createVisit(){
    var httpOptions={
      headers:new HttpHeaders({
        "Authorization" : "Bearer "+localStorage.getItem("token")
      })
    };
    var reqBody={
      year:this.year,
      month:this.month,
      day: this.day,
      startHour:this.startHour,
      endHour:this.endHour,
      startMinute:this.startMinute,
      endMinute:this.endMinute
    }
    this.http.post(environment.api+"/visit/create",reqBody,httpOptions).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }
  delete(){
    var httpOptions={
      headers:new HttpHeaders({
        "Authorization" : "Bearer "+localStorage.getItem("token")
      })
    };
    this.http.delete(environment.api+"/visit/delete?visitId="+this.visitId,httpOptions).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }
  add(){
    var httpOptions={
      headers:new HttpHeaders({
        "Authorization" : "Bearer "+localStorage.getItem("token")
      })
    };
    this.http.get(environment.api+"/visit/add?visitId="+this.visitId,httpOptions).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }
  remove(){
    var httpOptions={
      headers:new HttpHeaders({
        "Authorization" : "Bearer "+localStorage.getItem("token")
      })
    };
    this.http.get(environment.api+"/visit/remove?visitId="+this.visitId,httpOptions).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }
  profile(){
    var params:{year?:String,month?:String,day?:String};
    params={
      year:this.year,
      month:this.month,
      day:this.day,
    }
    this.getVisit(params,"")
  }
  details(){
    var params:{year?:String,month?:String,day?:String}={}
    params={
      year:this.year,
      month:this.month,
      day:this.day,
    }
    this.getVisit(params,"/admin")
  }

  getVisit(params:{year?:String,month?:String,day?:String},admin:String){
    var httpOptions={
      headers:new HttpHeaders({
        "Authorization" : "Bearer "+localStorage.getItem("token")
      })
    };
    var reqParams="";
    if (params.year){
      reqParams+="/day?year="+params.year+"&month="+params.month+"&day="+params.day;
    }

    this.http.get(environment.api+"/visit"+admin+"/all"+reqParams,httpOptions).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
  }
}
