import React , {useState} from "react";
import {Button, Text, View} from    "react-native";

const Cat = (props: any) => {
    const [isHungry, setIsHungry] = useState(true);

    return (
        <View>
            <Text>
                I am {props.name}, and I am {isHungry ? "hungry" : "full"}!
            </Text>
            <Button
                onPress={() => {
                    setIsHungry(false);
                }}
                title={isHungry ? "Feed me" : "Thank you!"}
            />
        </View>
    )
}

const State = () =>{
    return (
        <>
        <Cat name="Maru" />
        <Cat name="Brian" />
        <Cat name="Michael" />
        </>
    )
}

export default State;