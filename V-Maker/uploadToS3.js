const AWS = require('aws-sdk');
const s3 = new AWS.S3();

exports.uploadFiles = async function(data) {
    var files = data.images;
    var audio = data.audio;
    var username = data.userName;
    var promises = []
    promises.push(audioUpload(audio));
    files.forEach((file, index) => {
        promises.push(imageUpload(file, index))
    });
    
    return Promise.all(promises).then(response => {
        return response;
    })
}

imageUpload = (file, index) => {
    const base64File = new Buffer.from(file.replace(/^data:image\/\w+;base64,/, ""), 'base64');
    const type = file.split(';')[0].split('/')[1]; 
    const params = {
        Bucket: 'input12',
        Key:"Deep" +"/"+`${index + 1}.${type}`,
        Body: base64File,
        ACL: 'public-read',
        ContentEncoding: 'base64',
        ContentType: `image/${type}`,
    };

    console.log("Image" + index)

    return new Promise((resolve, reject) => {
        s3.putObject(params, (error, response) => {
            if (error) {
                return reject(error)
            } else if (response) {
                console.log("Call Back for image" + index)
                return resolve(response)
            }
        })
    }) 
}

audioUpload = (audio) => {
    const base64AudioFile = new Buffer.from(audio.replace(/^data:audio\/\w+;base64,/, ""), 'base64');
    const audioFileType = audio.split(';')[0].split('/')[1];
    
    const params = {
        Bucket: 'input12',
        Key:"Deep"+"/"+`audio.${audioFileType}`,
        Body: base64AudioFile,
        ACL: 'public-read',
        ContentEncoding: 'base64',
        ContentType: `audio/${audioFileType}`,
    };

    console.log("Audio")

    return new Promise((resolve, reject) => {
        s3.putObject(params, (error, response) => {
            if (error) {
                return reject(error)
            } else if (response) {
                console.log("Call Back for audio")
                return resolve(response)
            }
        })
    }) 
}