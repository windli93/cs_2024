import React from "react";
import { Text, View, ScrollView, Image, TextInput } from "react-native";
import Cat from "./components/cat";
import Dog from "./components/dog";
import Cafe from "./components/prop1";

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
    </ScrollView>
  );
}

export default App;
