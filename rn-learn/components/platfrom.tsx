import {Platform, StyleSheet} from 'react-native'

const style = StyleSheet.create({
    container: {
        flex: 1,
        ...Platform.select({
            ios: {
                backgroundColor: 'red'
            },
            android: {
                backgroundColor: 'blue'
            },
            default: {
                // other platforms, web for example
                backgroundColor: 'green'
            }
        })
    }
})

export default style;