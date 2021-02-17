$('#codelab-feedback').hide();
$("#dropleft").detach().appendTo("#codelab-title");
$(".time-remaining").hide();
$("#creatroom").click(function () {
    $('#exampleModal').modal('show')
});


$('#create-room-button').click(function (e) {
    // $.ajax({
    //     url: "/createRoom",
    //     type: "POST",
    //     data: JSON.stringify(lab),
    //     dataType: "json",
    //     contentType: "application/json",
    //     success: function (data) {
    //         $('#toast-title').text("Done")
    //         $('#toast-body').text("Lab has been added")
    //         $('#toast').toast('show')
    //         $('#exampleModal').modal('hide')
    //         $("#docID").text("")
    //         $("#description").text("")
    //         $('#add-lab-button').prop("disabled", false)
    //         $('#add-lab-button').html('Add')
    //     },
    //     error: function (e) {
    //         $('#add-lab-button').prop("disabled", false)
    //         $('#add-lab-button').html('Add')
    //
    //         $('#modal-error').text('Please check your input!')
    //     }
    // })
    writeUserData(1, "a", "a", "as")
})


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

var starCountRef = firebase.database().ref('users/' + postId + '/starCount');
starCountRef.on('value', (snapshot) => {
    const data = snapshot.val();
    updateStarCount(postElement, data);
});