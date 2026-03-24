import React from "react";
import { Text, View, TextInput, Image } from "react-native";

const dog = () => {
  return <View>
    <Text> Hello , I am a Dog 12131313</Text>
    <TextInput style={{height: 40, borderColor: 'gray', borderWidth: 1}} placeholder="Type here" />
    <Image source={{uri: 'https://reactnative.dev/img/tiny_logo.png'}} style={{width: 200, height: 200}} />
  </View>
};

export default dog;