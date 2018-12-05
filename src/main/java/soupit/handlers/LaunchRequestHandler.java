/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package main.java.soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import main.java.soupit.HilfsKlassen.*;
import main.java.soupit.Lists.Strings;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {
    public static final RezeptArrayList REZEPT_ARRAY_LIST = new RezeptArrayList();
    public static final String[] alleRezepte = {"kartoffelcremesuppe"};




    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = Strings.WELCOME;
        try {
            Object obj = new JSONParser().parse(new FileReader("main\\java\\rezepte.json"));
            JSONObject jsonObject = (JSONObject) obj;
            Map rezepte = (Map) jsonObject.get("rezepte");
            Map zutatenMitGeschlecht = (Map) jsonObject.get("zutaten");
            Map einheitenMitGeschlecht = (Map) jsonObject.get("einheiten");
            for (String s : alleRezepte) {
                addRecipes(s, rezepte,zutatenMitGeschlecht, einheitenMitGeschlecht);
            }
            speechText = (String) einheitenMitGeschlecht.get("kartoffel");
        } catch (Exception e) {
            speechText = e.getMessage();
        }

        return input.getResponseBuilder()
                .withSimpleCard("Soup IT", speechText)
                .withSpeech(speechText)
                .build();
    }
    private static void addRecipes(String rezeptname, Map rezepte, Map zutatenMitGeschlecht, Map einheitenMitGeschlecht) {


        Map rezept = (Map) rezepte.get(rezeptname);
        Map zutaten = (Map) rezept.get("zutaten");
        ZutatMengeEinheit zumeng[] = new ZutatMengeEinheit[zutaten.size()];

        Iterator<Map.Entry<String,Map>> it = zutaten.entrySet().iterator();
        int counter = 0;
        while(it.hasNext()){

            Map.Entry<String,Map> next = it.next();
            Map nextMap = next.getValue();
            String zutatString = (String) zutaten.keySet().toArray()[counter];
            Zutat zutat = new Zutat(zutatString,(String) zutatenMitGeschlecht.get(zutatString));
            String einheitString = (String) nextMap.get("einheit");
            Einheit einheit = new Einheit(einheitString,(String) einheitenMitGeschlecht.get(einheitString));
            double menge = Double.parseDouble((String)(nextMap.get("menge")));

            zumeng[counter] = new ZutatMengeEinheit(zutat,menge,einheit);
            counter++;
        }
        REZEPT_ARRAY_LIST.add(new Rezept(rezeptname,zumeng));



    }



}

