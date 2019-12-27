# Dev Evaluation Application
GQL/REST simple app to evaluate DEV troubleshooting skill.

This applications has a REST and a GraphQL service. At least one of them should be evaluated.

Go over the doc folder to check the Rest and GraphQl documentation.

This project aims to have the Developer answer these questions:
```
1 - Analyse the source code. documentation and execution from the project to say what the application does ?  (Could be a recorded video, or a textual description)
2 - Analyse the source code. documentation and execution from the project to say which bugs are happening on the application (could be a simple text description or the issue creation on GitHub)?  Can you fix it (push the modification to github)?
3 - What would be necessary to put this application on a cloud environment
```

# To generate OAuth token
- make a POST request to /oauth/token endpoint
- Sending with Basic Authentication, the username: contactClient and password: contactPassword values
- Sending also in the request body the attributes, to identify the user of the application:
```
grant_type = password
username = contactUser
password = contactPassword
scope = access
```
##  If you want to test the application with another user, you can generate the token with the following attributes.
```
   grant_type = password
   username = masterUser
   password = masterPassword
   scope = access
```


# To open the Rest documentation on Swagger IO
- Go over the doc folder, get the gql-dev-test.yaml
- Open the Swagger Editor  https://editor.swagger.io/  
- Click File -> Import File and select the file  gql-dev-test.yaml

# To open the Rest HTML documentation
- Go over the doc folder
- Open the rest_doc.html file

# To open the GraphQL documentation
- Go over the doc folder
- Open the gql_doc.html file