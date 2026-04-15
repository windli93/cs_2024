import React from "react";
import { Text, View, Button } from "react-native";

// 定义一个接受多种props的组件
interface PropsDemoProps {
  name: string;              // 字符串props
  age: number;               // 数字props
  isStudent: boolean;        // 布尔props
  hobbies: string[];         // 数组props
  address: {                 // 对象props
    city: string;
    street: string;
  };
  onGreet: (message: string) => void;  // 函数props
  children?: React.ReactNode;          // children props
}

const PropsDemo: React.FC<PropsDemoProps> = (props) => {
  const {
    name,
    age,
    isStudent,
    hobbies,
    address,
    onGreet,
    children
  } = props;

  const handlePress = () => {
    onGreet(`Hello from ${name}! I'm ${age} years old.`);
  };

  return (
    <View style={{ padding: 16, marginVertical: 8, backgroundColor: '#f0f0f0', borderRadius: 8 }}>
      <Text style={{ fontSize: 18, fontWeight: 'bold' }}>Props Demo Component</Text>
      
      <Text>Name: {name}</Text>
      <Text>Age: {age}</Text>
      <Text>Student: {isStudent ? 'Yes' : 'No'}</Text>
      
      <Text>Hobbies: {hobbies.join(', ')}</Text>
      <Text>Address: {address.city}, {address.street}</Text>
      
      <Button title="Greet" onPress={handlePress} />
      
      {children && (
        <View style={{ marginTop: 12, padding: 8, backgroundColor: '#e0e0e0', borderRadius: 4 }}>
          <Text style={{ fontWeight: 'bold' }}>Children Content:</Text>
          {children}
        </View>
      )}
    </View>
  );
};

// 使用示例组件
const PropsDemoExample = () => {
  const handleGreet = (message: string) => {
    console.log('Greeting:', message);
    alert(message);
  };

  const userInfo = {
    name: "Alice",
    age: 25,
    isStudent: true,
    hobbies: ["Reading", "Coding", "Hiking"],
    address: {
      city: "Beijing",
      street: "Zhongguancun"
    }
  };

  return (
    <View style={{ padding: 16 }}>
      <Text style={{ fontSize: 20, fontWeight: 'bold', marginBottom: 16 }}>Props 示例</Text>
      
      {/* 基本用法 */}
      <PropsDemo
        name={userInfo.name}
        age={userInfo.age}
        isStudent={userInfo.isStudent}
        hobbies={userInfo.hobbies}
        address={userInfo.address}
        onGreet={handleGreet}
      >
        <Text>这是通过children传递的内容</Text>
        <Text>可以传递任意React元素</Text>
      </PropsDemo>
      
      {/* 另一个实例展示不同数据 */}
      <PropsDemo
        name="Bob"
        age={30}
        isStudent={false}
        hobbies={["Gaming", "Music"]}
        address={{ city: "Shanghai", street: "Nanjing Road" }}
        onGreet={handleGreet}
      >
        <Text>另一个用户的children内容</Text>
      </PropsDemo>
      
      <View style={{ marginTop: 16, padding: 12, backgroundColor: '#e8f4fd', borderRadius: 8 }}>
        <Text style={{ fontWeight: 'bold' }}>Props 与 Java 多态的对比:</Text>
        <Text>1. Props 类似于函数参数，用于组件间数据传递</Text>
        <Text>2. Java 多态是同一接口的不同实现</Text>
        <Text>3. React 组件通过props实现配置和重用</Text>
        <Text>4. 两者都支持"一次定义，多种使用"的理念</Text>
      </View>
    </View>
  );
};

export default PropsDemoExample;