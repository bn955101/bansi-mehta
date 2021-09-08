const express = require('express')
const user = require('./api/routes/users')
const http = require('http')
const path = require('path');
const session = require('express-session');
const dotenv = require("dotenv");
const app = express();
const mysql = require('mysql');
const cors = require('cors');
let bodyParser = require("body-parser");

const fs = require("fs").promises;
const retrieveSecrets = require("./retriveSecrets");

//routes to verify that the secrets were retrieved successfully.
app.get("/", (req, res) => {
  return res.status(200).json({
    SECRET_1: process.env.SECRET_1,
    SECRET_2: process.env.SECRET_2,
  });
});

app.set('port', process.env.PORT || 8080);
var jsonParser = bodyParser.json({limit:1024*1024*10, type:'application/json'}); 
var urlencodedParser = bodyParser.urlencoded({ extended:true,limit:1024*1024*10,type:'application/x-www-form-urlencoded' });
app.use(jsonParser);
app.use(urlencodedParser);

app.use(express.static('public'))
app.use(cors())

app.use(session({
  secret: 'assignment',
  resave: false,
  saveUninitialized: true,
  cookie: { maxAge: 60000, token: "" }
}))

app.post('/register', user.register);
app.post('/create', user.create);
app.post('/sendNotification', user.sendNotification);

// app.listen(8080, async () => {
//   try {
//     const secretsString = await retrieveSecrets();
//     await fs.writeFile(".env", secretsString);
//     dotenv.config();
//     credentials = secretsString.split('\n')
//     let userName = ''
//     let password = ''
//     let host = ''
//     let database = ''
//     credentials.forEach(row => {
//       col = row.split('=')
//       if (col[0] == 'username') {
//         userName = col[1]
//       } else if (col[0] == 'password') {
//         password = col[1]
//       } else if (col[0] == 'host') {
//         host = col[1]
//       } else if (col[0] == 'dbInstanceIdentifier') {
//         database = col[1]
//       }
//     })
//     let connection = mysql.createConnection({
//       host: host,
//       user: userName,
//       password: password,
//       database: 'users'
//     });

//     connection.connect();

//     global.db = connection;

//     console.log("Server running on port 8080");
//   } catch (error) {
//     //log the error and crash the app
//     console.log("Error in setting environment variables", error);
//     process.exit(-1);
//   }
// });

app.listen(8080);

console.log("Server started...")