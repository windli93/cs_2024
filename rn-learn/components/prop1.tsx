import React from "react";
import { Text, View } from "react-native";

const Prop1 = (props: { name: string }) => {
    return (
        <View>
            <Text>Hello, My name is {props.name}</Text>
        </View>
    );
};

const Cafe = () => {
    return (
        <View>
            <Prop1 name="Maru" />
            <Prop1 name="Brian" />
            <Prop1 name="Michael" />
        </View>
    );
};

export default Cafe;