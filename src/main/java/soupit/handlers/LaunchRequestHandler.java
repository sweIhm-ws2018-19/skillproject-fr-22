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

import soupit.PersistentAttributes;
import soupit.SessionAttributes;
import soupit.recipe.*;
import soupit.Lists.Strings;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;


import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {




    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        readJson();
        String speechText = Strings.WELCOME ;

        Map<String, Object> persistentAttributes = input.getAttributesManager().getPersistentAttributes();
        if(persistentAttributes.get("programState") == null){
            persistentAttributes.put("programState", Strings.INITIAL_STATE);
            input.getAttributesManager().setPersistentAttributes(persistentAttributes);
            input.getAttributesManager().savePersistentAttributes();
        }
        if (PersistentAttributes.getRecipeName(input) != null){
            speechText = "möchtest du mit der Zubereitung deiner "+ PersistentAttributes.getRecipeName(input)+" fortfahren?";
        }




        return input.getResponseBuilder()
                .withSimpleCard("Soup IT", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }



    private void readJson() {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("data/rezepte.json");
            Object obj = new JSONParser().parse(new InputStreamReader(stream));
            JSONObject jsonObject = (JSONObject) obj;
            Map rezepte = (Map) jsonObject.get("rezepte");
            Map zutaten = (Map) jsonObject.get("zutaten");
            Map einheiten = (Map) jsonObject.get("einheiten");
            Object [] alleRezepte =  ((Map) jsonObject.get("rezepte")).keySet().toArray();
            for (Object o : alleRezepte) {
                addRecipes(o.toString(), rezepte,zutaten, einheiten);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private static void addRecipes(String rezeptname, Map rezepte, Map alleZutaten, Map<String,Map<String,String>> einheiten) {


        Map rezept = (Map) rezepte.get(rezeptname);
        Map zutaten = (Map) rezept.get("zutaten");
        Map jsonsteps  = (Map) rezept.get("schritte");
        Object[] objectsteps = jsonsteps.values().toArray();
        String[] steps = new String[objectsteps.length];
        for(int i=0; i <objectsteps.length; i++){
            steps[i] = objectsteps[i].toString();
        }
        ZutatMengeEinheit zumeng[] = new ZutatMengeEinheit[zutaten.size()];

        Iterator<Map.Entry<String, Map>> it = zutaten.entrySet().iterator();                                //TODO foreachloop better
        int counter = 0;
        while (it.hasNext()) {

            Map.Entry<String, Map> next = it.next();
            Map nextMap = next.getValue();
            String zutatString = (String) zutaten.keySet().toArray()[counter];
            Zutat zutat = new Zutat(zutatString, (String) alleZutaten.get(zutatString));
            String einheitString = (String) nextMap.get("einheit");
            Einheit einheit = new Einheit(einheitString,einheiten.get(einheitString).get("gender"),einheiten.get(einheitString).get("plural"));
            double menge = Double.parseDouble((String) (nextMap.get("menge")));
            if (menge == 0.3) menge = 1/3d;
            if (menge == 0.15) menge = 1/6d;

            zumeng[counter] = new ZutatMengeEinheit(zutat, menge, einheit);
            counter++;
        }
        SessionAttributes.recipes.add(new Rezept(rezeptname,steps, zumeng));


    }




}

//persat
// rezeptname, stepcount

