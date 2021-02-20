var refUsers;
var refChat;

var labid = "1EEGARIc9dEj9mpnmKoYP8n4EA9KNH9qR0W2c6CYEWT0";
var roomid = '7OkmUq'

$(function () {

    $('#codelab-feedback').hide();
    $("#topButton").detach().appendTo("#codelab-title");

    $("#creatroom").click(function () {
        $('#exampleModal').modal('show')
    });
    $('#create-room-button').click(function (e) {
        createRoom(labid, makeid(6));
    })
    $('.steps ol li').click(function (e) {
        updateStep($(this).index());
    });

    $('.toast').toast()


    var firstEnterRoom = true
    $('#btnRoom').click(function () {
        if (firstEnterRoom) {
            showChat($('#chat0'), "all")
            firstEnterRoom = false;
        }
    })
    $('.steps ol li a').append("\n" +
        "<span class=\"badge badge-secondary bg-secondary my-badge invisible\" onmouseover=\"hoverdiv(this,'divtoshow')\" onmouseout=\"hoverdiv(this,'divtoshow')\">0</span>")

    firebase.auth().onAuthStateChanged(function (user) {
        if (user) {
            currentUser = user;
            afterLogin(user);
            refUsers = firebase.database().ref('/labs/' + labid + '/' + roomid + '/users');
            refUsers.on('value', (snapshot) => {
                const data = snapshot.val();
                var count = []
                var totalUser = 0;
                $('#usersChat').empty()
                var userinStep = "";
                for (var uid in data) {
                    var step = data[uid].step;
                    if (count[step] == undefined)
                        count[step] = {count: 0, user: ""};
                    count[step].count++;
                    count[step].user = count[step].user + data[uid].name + "<br>";
                    totalUser++;
                    //Add to chat room
                    if (currentUser.uid != uid)
                        $('#usersChat').append("<a href='#' onclick='showChat(this,\"" + uid + "\")' class=\"list-group-item list-group-item-action rounded-0 media uchat\"><img src=\"" + data[uid].photo + "\" alt=\"user\" width=\"40\" height=\"40\"  class=\"rounded-circle\"><div class=\"media-body\">" + data[uid].name + "</div></a>")

                }
                $('.speech-bubble').html(userinStep)

                for (let i = 1; i <= getNumberOfSteps(); i++) {
                    if (count[i - 1] == undefined)
                        $('li:nth-child(' + i + ') > a > span.badge').addClass("invisible")
                    else {
                        $('li:nth-child(' + i + ') > a > span.badge').removeClass("invisible")
                        $('li:nth-child(' + i + ') > a > span.badge').text(count[i - 1].count);
                        $('li:nth-child(' + i + ') > a > span.badge').attr("user", count[i - 1].user)

                    }
                }
                $('#numOnline').text(totalUser)
            });
            var leave = {};
            leave['users/' + currentUser.uid] = null;
            refUsers.onDisconnect().update(leave);
            updateStep(getSelectedStep());
            //Listen to Notification
            var first = true;
            firebase.database().ref('/notifies/' + currentUser.uid).on('value', (snapshot) => {
                if (!first) {
                    const data = snapshot.val();
                    if (!$("#collapse-online").hasClass("show") || ($("#collapse-online").hasClass("show") && sendTo !== data.uid)) {
                        $("#toastTitle").text(data.uname);
                        $("#toastBody").text(data.message);
                        $('.toast').toast('show');
                    }
                }
                first = false;
            });

            var firstAll = true;
            firebase.database().ref('/labs/' + labid + '/' + roomid + '/notifies/all').on('value', (snapshot) => {
                if (!firstAll) {
                    if (!$("#collapse-online").hasClass("show") || ($("#collapse-online").hasClass("show") && sendTo !== "all")) {
                        const data = snapshot.val();
                        $("#toastTitle").text("Chat room");
                        $("#toastBody").text(data.message);
                        $('.toast').toast('show');
                    }
                }
                firstAll = false;
            });


        } else {
            // No user is signed in.
        }
    });

});

//Chat
var chatroom;
var sendTo;

function showmess(e) {
    e.preventDefault();
    alert("d")
    return false;
}

function showChat(me, uid) {
    sendTo = uid;
    if (refChat != null)
        refChat.off()
    $('#chatMessages').empty();
    if (uid === "all") {
        refChat = firebase.database().ref('/labs/' + labid + '/' + roomid + '/chats/all/');
        chatroom = uid;
    } else {
        if (uid > currentUser.uid)
            chatroom = uid + "-" + currentUser.uid;
        else
            chatroom = currentUser.uid + "-" + uid
        refChat = firebase.database().ref('/chats/' + chatroom);  //Private chat
    }

    refChat.on('child_added', (data) => {
        showMessage(data.val());
    });
    $(".uchat").removeClass("active text-white")
    $(me).addClass("active text-white")
}

function sendMessage() {
    if ($('#txtMessage').val().trim() !== "") {
        var change = {};
        change[refChat.push().key] = {
            uid: currentUser.uid,
            name: currentUser.displayName,
            photo: currentUser.photoURL,
            time: firebase.database.ServerValue.TIMESTAMP,
            message: $('#txtMessage').val()
        };
        refChat.update(change);
        var ref;
        if (sendTo === "all") {
            ref = firebase.database().ref('/labs/' + labid + '/' + roomid + '/notifies/all')
        } else {
            ref = firebase.database().ref('/notifies/' + sendTo)
        }
        ref.set({
            uid: currentUser.uid,
            uname: currentUser.displayName,
            message: $('#txtMessage').val(),
            time: firebase.database.ServerValue.TIMESTAMP
        });
        $('#txtMessage').val("")

        //REMOVE OLD CHAT
        const MAX_COUNT = 99;  //Keep 100 recent
        refChat.once('value', function (snapshot) {
            if (snapshot.numChildren() > MAX_COUNT) {
                var childCount = 0;
                var updates = {};
                snapshot.forEach(function (child) {
                    if (++childCount < snapshot.numChildren() - MAX_COUNT) {
                        updates[child.key] = null;
                    }
                });
                refChat.update(updates);
            }
        });
    }
}

function showMessage(data) {
    if (currentUser.uid === data.uid)
        $('#chatMessages').append("<div class=\"ml-auto d-flex justify-content-end\"><div class=\"chat-body\"><div class=\"bg-primary rounded-pill py-2 px-3  text-white text-small\">" + data.message + "</div><span class=\"text-muted d-flex justify-content-end chat-time\">" + time_ago(data.time) + "</span></div></div>\n")
    else
        $('#chatMessages').append("<div class=\"media w-75 \"><img src=\"" + data.photo + "\"  width=\"40\" height=\"40\" class=\"rounded-circle\"><div class=\"media-body ml-3\"><div class=\"bg-light rounded-pill py-2 px-3\"><span class=\"text-small mb-0 text-muted\">" + data.message + "</span></div><p class=\"text-muted chat-time\">" + time_ago(data.time) + "</p></div></div>");
    var objDiv = document.getElementById("chatMessages");
    objDiv.scrollTop = objDiv.scrollHeight;
}


function getNumberOfSteps() {
    var radioButtons = $(".steps ol li");
    return radioButtons.length;
}

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
        change[currentUser.uid] = {
            step: step,
            time: firebase.database.ServerValue.TIMESTAMP,
            name: currentUser.displayName,
            photo: currentUser.photoURL
        };
        refUsers.update(change);
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


function time_ago(time1) {

    time = new Date(time1);

    return TimeAgo.inWords(time.getTime());
}

var TimeAgo = (function () {
    var self = {};

    // Public Methods
    self.locales = {
        prefix: '',
        sufix: 'ago',

        seconds: 'less than a min',
        minute: 'about a minute',
        minutes: '%d minutes',
        hour: 'about an hour',
        hours: 'about %d hours',
        day: 'a day',
        days: '%d days',
        month: 'about a month',
        months: '%d months',
        year: 'about a year',
        years: '%d years'
    };

    self.inWords = function (timeAgo) {
        var seconds = Math.floor((new Date() - parseInt(timeAgo)) / 1000),
            separator = this.locales.separator || ' ',
            words = this.locales.prefix + separator,
            interval = 0,
            intervals = {
                year: seconds / 31536000,
                month: seconds / 2592000,
                day: seconds / 86400,
                hour: seconds / 3600,
                minute: seconds / 60
            };

        var distance = this.locales.seconds;

        for (var key in intervals) {
            interval = Math.floor(intervals[key]);

            if (interval > 1) {
                distance = this.locales[key + 's'];
                break;
            } else if (interval === 1) {
                distance = this.locales[key];
                break;
            }
        }

        distance = distance.replace(/%d/i, interval);
        words += distance + separator + this.locales.sufix;

        return words.trim();
    };

    return self;
}());

function hoverdiv(e, divid) {

    var left = 40 + $(e).offset().left + "px";
    var top = $(e).offset().top + "px";


    var div = document.getElementById(divid);
    div.innerHTML = $(e).attr("user")

    div.style.left = left;
    div.style.top = top;

    $("#" + divid).toggle();
    return false;
}

