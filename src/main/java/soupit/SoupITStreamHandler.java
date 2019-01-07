/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package soupit;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import soupit.handlers.*;


public class SoupITStreamHandler extends SkillStreamHandler {


    private static Skill getSkill() {

        return Skills.standard().addRequestHandlers(
                new PauseIntent(),
                new LaunchRequestHandler(),
                new CancelandStopIntentHandler(),
                new SessionEndedRequestHandler(),
                new HelpIntentHandler(),
                new FallbackIntentHandler(),
                new IngredientIntentHandler(),
                new NameRecipeIntent(),
                new YesNoIntent(),
                new MoreRecipesIntent(),
                new NumberOfServingsIntent(),
                new StartCookingIntent(),
                new NextStepIntent(),
                new RepeatIntent()
        )
                .withSkillId("amzn1.ask.skill.88e3acae-7fca-4ec5-985c-220521f6d2ef")
                .withTableName("dynamoRezepteTabelle")
                .withAutoCreateTable(true)
                .build();
    }

    public SoupITStreamHandler() {
        super(getSkill());
    }

}