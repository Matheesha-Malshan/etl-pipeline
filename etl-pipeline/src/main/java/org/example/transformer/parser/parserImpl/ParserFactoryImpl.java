package org.example.transformer.parser.parserImpl;

import org.example.model.Filetype;
import org.example.transformer.parser.ParserFactory;
import org.example.transformer.parser.ParserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class ParserFactoryImpl implements ParserFactory {

    HashMap <Filetype,Object> map=new HashMap<>();

   ParserFactoryImpl(List<ParserService> objectList){

       for (ParserService obj:objectList){

           for (Filetype filetype:Filetype.values()){
               if(obj.checkType(filetype)){
                  map.put(filetype,obj);
               }
           }
       }

   }

    public ParserService getObject(Filetype filetype){
       return (ParserService) map.get(filetype);
   }

}
