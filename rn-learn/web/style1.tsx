import React from 'react';
import { StyleSheet, View, Text } from 'react-native';

const MyComponent = () => (
    <View style={styles.container}>
        <Text>12313131231</Text>
        <View style={{flex: 1, backgroundColor: 'red'}} />
    </View>
);

const styles = StyleSheet.create({
    container: {
        flex: 1,
        paddingTop: 20,
    }
});

export default styles;