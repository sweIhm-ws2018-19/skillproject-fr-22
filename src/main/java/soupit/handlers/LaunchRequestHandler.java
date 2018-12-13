/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package soupit.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import soupit.recipe.*;
import soupit.Lists.Strings;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;


import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {
    public static final RezeptArrayList REZEPT_ARRAY_LIST = new RezeptArrayList();
    public static final String[] alleRezepte = {"kartoffelcremesuppe", "möhrencremesuppe","zucchinicremesuppe","möhren und kartoffeleintopf","tomatensuppe","tomaten kokos suppe"};




    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        readJson();
        String speechText = Strings.WELCOME;
        return input.getResponseBuilder()
                .withSimpleCard("Soup IT", speechText)
                .withSpeech(speechText)
                .build();
    }

    private void readJson() {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("data/rezepte.json");
            Object obj = new JSONParser().parse(new InputStreamReader(stream));
            JSONObject jsonObject = (JSONObject) obj;
            Map rezepte = (Map) jsonObject.get("rezepte");
            Map zutatenMitGeschlecht = (Map) jsonObject.get("zutaten");
            Map einheitenMitGeschlecht = (Map) jsonObject.get("einheiten");
            for (String s : alleRezepte) {
                addRecipes(s, rezepte,zutatenMitGeschlecht, einheitenMitGeschlecht);
            }
        } catch (Exception e) {
            e.getMessage();
        }
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

