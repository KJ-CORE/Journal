package com.aitpune.Journal.api.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
 public class WeatherResponse{

     private Current current;
    @Getter
    @Setter
     public class Current{
         private int temperature;

    }
 }





