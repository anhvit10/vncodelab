$(function () {
    page = "index";


});
async function addLab() {
    var docID = document.getElementById("docID").value;
    var description = document.getElementById("description").value;
    var cateID = document.getElementById("cateID").value
    let formData = new FormData();
    formData.append("docID", docID);
    formData.append("description", description);
    formData.append("cateID", cateID);
    let response = await fetch('/save', {
        method: "POST",
        body: formData
    });
    $('#exampleModal').hide();
    $('.modal-backdrop').hide();
    if(response.status == 200) {
        alert("Lab successfully created.");
    }
    // var lab = {}
    // lab["docID"] = $("#docID").val();
    // lab["description"] = $("#description").val();
    // lab["cateID"] = $("#cateID").val();
    // $(this).prop("disabled", true);
    // $(this).html('<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Adding...');
    // $.ajax({
    //     url: "/save",
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
    //         $('#modal-error').text('Please check your input!')
    //     }
    // })
}

