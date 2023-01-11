
function btn1(zone){
    $(".card-group").empty();
    $.ajax({
        url: 'http://127.0.0.1:8080/SightAPI',
        type: 'get',
        success: function(data){
          console.log(data);
          for(i = 0;i<41;i++){
              if(data[i].zone==zone){
                    //console.log(data[i]);
                    $('.card-group').append(
                        '<div class="col-12 col-sm-4">'+
                            '<div class="card border-dark mb-3" style="width: 21rem;">'+
                                '<div class="card-body">'+
                                    '<div class="card-header">'+data[i].sightName+'</div>'+
                                    '<p class="card-text">'+data[i].zone+'</p>'+
                                    '<p class="card-text">'+data[i].catagory+'</p>'+
                                    '<a href="https://www.google.com/maps/search/?api=1&query='+data[i].address+'" class="btn btn-link">'+data[i].address+'</a>'+'<br>'+
                                    '<button class="btn btn-outline-secondary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample'+i+'" aria-expanded="false" aria-controls="collapseExample">'+
                                        "詳細資訊"+
                                    '</button>'+
                                '</div>'+
                                '<div class="collapse" id="collapseExample'+i+'">'+
                                    '<img src= "'+data[i].photoURL+'" class="card-img-top" >'+
                                  '<div class="card card-body">'+
                                    data[i].description+
                                  '</div>'+
                                '</div>'+
                            '</div>'+
                        '</div>'
                    )
              }

          }
        },
        error: function(){
          //alert("wrong");
        }
      })
}

/*
fetch('http://127.0.0.1:8080/SightAPI?zone=中山',{mode:"cors"})
    .then((response) => {

        return response.json();
    })
    .then( (response) => {
        console.log(response);
    })
    .catch((error) => {
        //console.log(`Error: ${error}`);
    })

*/
 //alert("hello");