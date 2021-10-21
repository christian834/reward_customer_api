# Rewards points for customer
This is a simple app that calculate the customer's reward points based in dollar spends 
For that we use this function to calculate the rewards points

![equation](https://latex.codecogs.com/gif.latex?%5Cbegin%7Bmatrix%7D%20%26%20%5C%5C%20RP%20%3D%20%28DS%20-%20100%29*2%20&plus;50%20%26%20%5C%5C%20RP%20%3D%20DS%20-%2050%20%5Cend%7Bmatrix%7D%20%5Cbegin%7BBmatrix%7D%20%26%20%5C%5C%20DS%20%3E%20100%20%26%20%5C%5C%2050%20%3E%20DS%20%3E%20100%20%5Cend%7BBmatrix%7D)

# Dependencies

- JAVA 8+
- Gradle 7.1+


# Instalation 

```sh
cd reward_customer_api
./gradle build
node app
```

After that run RewardCustomerApplication it will start Spring boot app

# Test Set

There are some test for the app you can see it in test folder