var host;
if (location.href.includes('github')) {
    host = "https://restbuddy.herokuapp.com/";
} else {
    host = "http://localhost:7777/";
}
//host = "https://restbuddy.herokuapp.com/";

(function ($) {
    "use strict";

    // Preloader (if the #preloader div exists)
    $(window).on('load', function () {
        $('#responseLoader').hide();
        $('#api2').click();

        if ($('#preloader').length) {
            $('#preloader').delay(100).fadeOut('slow', function () {
                $(this).hide();
            });
        }
    });

    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({
            scrollTop: 0
        }, 1500, 'easeInOutExpo');
        return false;
    });

    // Initiate the wowjs animation library
    new WOW().init();

    // Header scroll class
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('#header').addClass('header-scrolled');
        } else {
            $('#header').removeClass('header-scrolled');
        }
    });

    if ($(window).scrollTop() > 100) {
        $('#header').addClass('header-scrolled');
    }

    // Smooth scroll for the navigation and links with .scrollto classes
    $('.main-nav a, .mobile-nav a, .scrollto').on('click', function () {
        if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
            var target = $(this.hash);
            if (target.length) {
                var top_space = 0;

                if ($('#header').length) {
                    top_space = $('#header').outerHeight();

                    if (!$('#header').hasClass('header-scrolled')) {
                        top_space = top_space - 40;
                    }
                }

                $('html, body').animate({
                    scrollTop: target.offset().top - top_space
                }, 1500, 'easeInOutExpo');

                if ($(this).parents('.main-nav, .mobile-nav').length) {
                    $('.main-nav .active, .mobile-nav .active').removeClass('active');
                    $(this).closest('li').addClass('active');
                }

                if ($('body').hasClass('mobile-nav-active')) {
                    $('body').removeClass('mobile-nav-active');
                    $('.mobile-nav-toggle i').toggleClass('fa-times fa-bars');
                    $('.mobile-nav-overly').fadeOut();
                }
                return false;
            }
        }
    });

    // Navigation active state on scroll
    var nav_sections = $('section');
    var main_nav = $('.main-nav, .mobile-nav');
    var main_nav_height = $('#header').outerHeight();

    $(window).on('scroll', function () {
        var cur_pos = $(this).scrollTop();

        nav_sections.each(function () {
            var top = $(this).offset().top - main_nav_height,
                bottom = top + $(this).outerHeight();

            if (cur_pos >= top && cur_pos <= bottom) {
                main_nav.find('li').removeClass('active');
                main_nav.find('a[href="#' + $(this).attr('id') + '"]').parent('li').addClass('active');
            }
        });
    });

    // jQuery counterUp (used in Whu Us section)
    $('[data-toggle="counter-up"]').counterUp({
        delay: 10,
        time: 1000
    });

    // Porfolio isotope and filter
    $(window).on('load', function () {
        var portfolioIsotope = $('.portfolio-container').isotope({
            itemSelector: '.portfolio-item'
        });
        $('#portfolio-flters li').on('click', function () {
            $("#portfolio-flters li").removeClass('filter-active');
            $(this).addClass('filter-active');

            portfolioIsotope.isotope({
                filter: $(this).data('filter')
            });
        });
    });

    // Testimonials carousel (uses the Owl Carousel library)
    $(".testimonials-carousel").owlCarousel({
        autoplay: true,
        dots: true,
        loop: true,
        items: 1
    });

    // Clients carousel (uses the Owl Carousel library)
    $(".clients-carousel").owlCarousel({
        autoplay: true,
        dots: true,
        loop: true,
        responsive: {
            0: {
                items: 2
            },
            768: {
                items: 4
            },
            900: {
                items: 6
            }
        }
    });

})(jQuery);



function randomNumberFromRange(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}

// All Cities
$('[id^=api]').click(function () {
    $('#responseLoader').show();

})

function preCall() {
    $('#requestEndpointID').html('');
    $('#responseDataID').html('');
    $('#responseStatusID').html('');
}

$('#api1').click(function () {
    preCall();
    var startTime = Date.now();
    var api = host + "city/allcities";
    var requestBody = "";
    var method = `<h5 class="d-inline">Method: </h5> GET`;

    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.get(api)
        .then(function (response) {
            // handle success
            $('#responseStatusID').html(response.status);
            $('#responseDataID').html('<pre><code>' + JSON.stringify(response.data, null, 2) + '</code></pre>');
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });

});


// City by ID
$('#api2').click(function () {
    preCall();
    var startTime = Date.now();
    var randomNum = randomNumberFromRange(1, 1300);
    var api = host + "city/" + randomNum;
    var requestBody = "";
    var method = `<h5 class="d-inline">Method: </h5> GET`;


    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.get(api)
        .then(function (response) {
            // handle success
            $('#responseStatusID').html(response.status);
            $('#responseDataID').html('<pre><code>' + JSON.stringify(response.data, null, 2) + '</code></pre>');
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });


});


// Add new city
$('#api3').click(function () {
    preCall();
    var startTime = Date.now();
    //var randomNum = randomNumberFromRange(1, 1300);
    var api = host + "city/addcity";
    var reqBody = {
        cityname: "New Post City",
        statecode: "NC",
        statename: "New Post state",
        latitude: "00.0000",
        longitude: "-00000",
        population: 18880,
        density: 187870,
        zipcode: "00000 11111"

    };

    var requestBody = `<h5 class="d-inline">Request Body: </h5><pre style="color:#1bb1dc">${JSON.stringify(reqBody,null,2)}</pre>`;
    var method = `<h5 class="d-inline">Method: </h5> <span style="color:#1bb1dc">POST</span>`;

    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.post(api, reqBody)
        .then(function (response) {
            // handle success
            $('#responseStatusID').html(response.status);
            $('#responseDataID').html('<pre><code>' + JSON.stringify(response.data, null, 2) + '</code></pre>');
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });

});

// Delete a city
$('#api4').click(function () {
    preCall();
    var startTime = Date.now();
    var randomNum = randomNumberFromRange(1, 1300);
    var api = host + "city/" + randomNum;


    var requestBody = "";
    var method = `<h5 class="d-inline">Method: </h5> <span style="color:#1bb1dc">DELETE</span>`;


    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.delete(api)
        .then(function (response) {
            // handle success
            $('#responseStatusID').html(response.status);
            $('#responseDataID').html(response.data);
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });


});

// Update City Details (All Fields)
$('#api5').click(function () {
    preCall();
    var startTime = Date.now();
    //var randomNum = randomNumberFromRange(1, 1300);
    var api = host + "city/14";
    var reqBody = {
        cityname: "New Put City",
        statecode: "NC",
        statename: "New state",
        latitude: "00.0000",
        longitude: "-00000",
        population: 0,
        density: 0,
        zipcode: "00000 11111"
    };
    var requestBody = `<h5 class="d-inline">Request Body: </h5><pre style="color:#1bb1dc">${JSON.stringify(reqBody,null,2)}</pre>`;
    var method = `<h5 class="d-inline">Method: </h5> <span style="color:#1bb1dc">PUT</span>`;


    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.put(api, reqBody)
        .then(function (response) {
            // handle success
            $('#responseStatusID').html(response.status);
            $('#responseDataID').html('<pre><code>' + JSON.stringify(response.data, null, 2) + '</code></pre>');
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });

});

// Update City Details (Some Fields)
$('#api6').click(function () {
    preCall();
    var startTime = Date.now();
    //var randomNum = randomNumberFromRange(1, 1300);
    var api = host + "city/15";
    var reqBody = {
        cityname: "New Patch City"
    };
    var requestBody = `<h5 class="d-inline">Request Body: </h5><pre style="color:#1bb1dc">${JSON.stringify(reqBody,null,2)}</pre>`;
    var method = `<h5 class="d-inline">Method: </h5> <span style="color:#1bb1dc">PATCH</span>`;


    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.patch(api, reqBody)
        .then(function (response) {
            // handle success
            $('#responseStatusID').html(response.status);
            $('#responseDataID').html('<pre><code>' + JSON.stringify(response.data, null, 2) + '</code></pre>');
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });

});


// City by CityName
$('#api7').click(function () {
    preCall();
    var startTime = Date.now();
    var api = host + "city/cityname/Houston";

    var requestBody = "";
    var method = `<h5 class="d-inline">Method: </h5> GET`;


    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.get(api)
        .then(function (response) {
            // handle success
            $('#responseStatusID').html(response.status);
            $('#responseDataID').html('<pre><code>' + JSON.stringify(response.data, null, 2) + '</code></pre>');
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });


});



// City by Population Greater Than
$('#api8').click(function () {
    preCall();
    var startTime = Date.now();
    var api = host + "city/population/17687";

    var requestBody = "";
    var method = `<h5 class="d-inline">Method: </h5> GET`;


    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.get(api)
        .then(function (response) {
            // handle success
            $('#responseStatusID').html(response.status);
            $('#responseDataID').html('<pre><code>' + JSON.stringify(response.data, null, 2) + '</code></pre>');
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });


});


// City by Population Greater Than
$('#api9').click(function () {
    preCall();
    var startTime = Date.now();
    var api = host + "city/zipcode/85009";

    var requestBody = "";
    var method = `<h5 class="d-inline">Method: </h5> GET`;


    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.get(api)
        .then(function (response) {
            // handle success
            $('#responseStatusID').html(response.status);
            $('#responseDataID').html('<pre><code>' + JSON.stringify(response.data, null, 2) + '</code></pre>');
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });


});



//Successful Authentication
$('#api10').click(function () {
    preCall();
    var startTime = Date.now();
    //var randomNum = randomNumberFromRange(1, 1300);
    var api = host + "city/login";

    var reqBody = {
        email: 'prabhu.sites@gmail.com',
        password: '123'
    };


    var requestBody = `<h5 class="d-inline">Request Body: </h5><pre style="color:#1bb1dc">${JSON.stringify(reqBody,null,2)}</pre>`;

    var method = `<h5 class="d-inline">Method: </h5> <span style="color:#1bb1dc">POST</span>`;

    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.post(api, reqBody)
        .then(function (response) {
            // handle success
            $('#responseStatusID').html(response.status);
            $('#responseDataID').html('<pre><code>' + response.data + '</code></pre>');
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            console.log(error);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });

});



//unsuccessful Authorization
$('#api11').click(function () {
    preCall();
    var startTime = Date.now();
    //var randomNum = randomNumberFromRange(1, 1300);
    var api = host + "city/login";
    var reqBody = {
        email: 'prabhu@gmail.com',
        password: '123'
    };

    var requestBody = `<h5 class="d-inline">Request Body: </h5><pre style="color:#1bb1dc">${JSON.stringify(reqBody,null,2)}</pre>`;
    var method = `<h5 class="d-inline">Method: </h5> <span style="color:#1bb1dc">POST</span>`;

    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.post(api, reqBody)
        .then(function (response) {
            // handle success
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            $('#responseStatusID').html(error.response.status);
            $('#responseDataID').html('<pre><code>' + error.response.data + '</code></pre>');
            console.log(error.response);

        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });

});


//unsuccessful Authentication
$('#api12').click(function () {
    preCall();
    var startTime = Date.now();
    //var randomNum = randomNumberFromRange(1, 1300);
    var api = host + "city/login";
    var reqBody = {
        email: "prabhu.random@gmail.com",
        password: "12jh3"
    };

    var requestBody = `<h5 class="d-inline">Request Body: </h5><pre style="color:#1bb1dc">${JSON.stringify(reqBody,null,2)}</pre>`;
    var method = `<h5 class="d-inline">Method: </h5> <span style="color:#1bb1dc">POST</span>`;
    $('#requestEndpointID').html(`<h5 class="d-inline">Endpoint: </h5><a href="${api}" target="_blank">${api}</a><br>${method}<br>${requestBody}`);

    axios.post(api, reqBody)
        .then(function (response) {
            // handle success
            console.log(response);
        })
        .catch(function (error) {
            // handle error
            $('#responseStatusID').html(error.response.status);
            $('#responseDataID').html('<pre><code>' + error.response.data + '</code></pre>');
            console.log(error.response);
        })
        .finally(function () {
            // always executed
            console.log('Time Taken (milisecs) by API to complete: ' + (Date.now() - startTime));
            $('#responseLoader').hide();
        });

});