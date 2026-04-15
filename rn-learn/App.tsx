import React from "react";
import { Text, View, ScrollView, Image, TextInput } from "react-native";
import Cat from "./components/cat";
import Dog from "./components/dog";
import Cafe from "./components/prop1";
import PropsDemoExample from "./components/propsDemo";
import State from "./components/statedemo1";
import PizzaTranslator from "./components/textInput";
import ScollView from "./components/scollView";
import Style from "./components/platfrom";

const App = () => {
  return (
    <ScrollView>
       <Text>Some Text</Text>
       <View>
           <Text>Some more Text</Text>
           <Image source={{uri: 'https://reactnative.dev/img/tiny_logo.png'}} style={{width: 200, height: 200}} />
       </View>
       <TextInput style={{height: 40, borderColor: 'gray', borderWidth: 1}} placeholder="Type here" />
        <Cat />
        <Dog />
        <Cafe />
        <PropsDemoExample />
        <State />
        <PizzaTranslator />
        <ScollView />
        <View style={Style.container} >
            <Text>Some Text</Text>
        </View>
     </ScrollView>
  );
}

export default App;
