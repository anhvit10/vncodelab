var firebaseConfig = {
    apiKey: "AIzaSyDj-QxkIp9-HSPRb3kqAmoRRpcx43Q_RGc",
    authDomain: "vncodelab.firebaseapp.com",
    databaseURL: "https://vncodelab.firebaseio.com",
    projectId: "vncodelab",
    storageBucket: "vncodelab.appspot.com",
    messagingSenderId: "665655741816",
    appId: "1:665655741816:web:a3996e52da431ce6bf8c8d"
};
var currentUser;
firebase.initializeApp(firebaseConfig);

$(function () {

    $('#btnLogin').click(function (e) {
        openLoginModal();
    });
    $('a#google_login').click(function (e) {
        var provider = new firebase.auth.GoogleAuthProvider();
        firebase.auth()
            .signInWithPopup(provider)
            .then((result) => {
                var credential = result.credential;
                // This gives you a Google Access Token. You can use it to access the Google API.
                var token = credential.accessToken;
                // The signed-in user info.
                var user = result.user;
                afterLogin(user);
            }).catch((error) => {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
            // The email of the user's account used.
            var email = error.email;
            // The firebase.auth.AuthCredential type that was used.
            var credential = error.credential;
            // ...
        });
    });
    $('#signout').click(function (e) {
        firebase.auth().signOut().then(() => {
            $('#btnLogin').click(function (e) {
                openLoginModal();
            });
            $('#btnLogin').text("Đăng nhập");
            $('#btnLogin').addClass("rounded")
            $('#profilePicture').addClass("d-none")
            $('#btnLogin').removeClass("d-none")
            $('#collapse-profile').hide()
        }).catch((error) => {
            // An error happened.
        });
    })


    firebase.auth().onAuthStateChanged(function (user) {
        if (user) {
            currentUser = user;
            afterLogin(user);
        } else {
            $('#btnLogin').removeClass("d-none")
        }
    });
});



function afterLogin(user) {
    $('#loginModal').modal('hide')
    $('#btnLogin').addClass("d-none")
    $('#profilePicture').removeClass("d-none")
    $('#profilePicture').attr("src",user.photoURL)
    $('#profilePictureBig').attr("src",user.photoURL)
    $('#profileName').text(user.displayName)
    $('#profileEmail').text(user.email)
}

function showRegisterForm() {
    $('.loginBox').fadeOut('fast', function () {
        $('.registerBox').fadeIn('fast');
        $('.login-footer').fadeOut('fast', function () {
            $('.register-footer').fadeIn('fast');
        });
        $('.modal-title').html('Register with');
    });
    $('.error').removeClass('alert alert-danger').html('');

}

function showLoginForm() {
    $('#loginModal .registerBox').fadeOut('fast', function () {
        $('.loginBox').fadeIn('fast');
        $('.register-footer').fadeOut('fast', function () {
            $('.login-footer').fadeIn('fast');
        });

        $('.modal-title').html('Đăng nhập với');
    });
    $('.error').removeClass('alert alert-danger').html('');
}

function openLoginModal() {
    showLoginForm();
    setTimeout(function () {
        $('#loginModal').modal('show');
    }, 230);

}

function openRegisterModal() {
    showRegisterForm();
    setTimeout(function () {
        $('#loginModal').modal('show');
    }, 230);

}

function loginAjax() {
    /*   Remove this comments when moving to server
    $.post( "/login", function( data ) {
            if(data == 1){
                window.location.replace("/home");
            } else {
                 shakeModal();
            }
        });
    */

    /*   Simulate error message from the server   */
    shakeModal();
}

function shakeModal() {
    $('#loginModal .modal-dialog').addClass('shake');
    $('.error').addClass('alert alert-danger').html("Invalid email/password combination");
    $('input[type="password"]').val('');
    setTimeout(function () {
        $('#loginModal .modal-dialog').removeClass('shake');
    }, 1000);
}



