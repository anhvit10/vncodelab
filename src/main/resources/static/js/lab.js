var ref;
$(function () {

    $('#codelab-feedback').hide();
    $("#login").detach().appendTo("#codelab-title");
    $(".time-remaining").hide();
    $("#creatroom").click(function () {
        $('#exampleModal').modal('show')
    });
    $('#create-room-button').click(function (e) {
        createRoom("1EEGARIc9dEj9mpnmKoYP8n4EA9KNH9qR0W2c6CYEWT0", makeid(6));
    })
    $('.steps ol li').click(function (e) {
        updateStep($(this).index());
    });
    firebase.auth().onAuthStateChanged(function (user) {
        if (user) {
            currentUser = user;
            afterLogin(user);
            ref = firebase.database().ref('/labs/1EEGARIc9dEj9mpnmKoYP8n4EA9KNH9qR0W2c6CYEWT0/7OkmUq');

            ref.on('value', (snapshot) => {
                const data = snapshot.val();
                var currentStep = getSelectedStep();
                var count = 0;
                for (var key in data.users) {
                    var step = data.users[key].step;
                    if (step == currentStep) {
                        count++;
                    }
                }
                $('#numOnline').innerText = count;
                $('#numOnline').text(count)

            });
            var leave = {};
            leave['users/' + currentUser.uid] = null;
            ref.onDisconnect().update(leave);
            updateStep(getSelectedStep());
        } else {
            // No user is signed in.
        }
    });

});


function getSelectedStep() {
    var radioButtons = $(".steps ol li");
    for (const element of radioButtons) {
        if (element.hasAttribute("selected"))
            return radioButtons.index(element)
    }
}

function updateStep(step) {
    if (currentUser != null) {
        var change = {};
        change['users/' + currentUser.uid] = {
            step: step,
            time: firebase.database.ServerValue.TIMESTAMP
        };
        ref.update(change);
    }
}

function createRoom(docID, roomID) {
    firebase.database().ref('labs/' + docID + "/" + roomID).set({
        create_time: firebase.database.ServerValue.TIMESTAMP
    });
}

function makeid(length) {
    var result = '';
    var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for (var i = 0; i < length; i++)
        result += characters.charAt(Math.floor(Math.random() * charactersLength));
    return result;
}

function writeLab(docID, room, user, step) {
    firebase.database().ref(docID + '/' + room).set({
        user: user,
        step: step,
    });
}

