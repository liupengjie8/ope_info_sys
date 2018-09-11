 $(document).ready(function () {
            var qcloud = {};
            $('[data-nav]').hover(function () {
                var _nav = $(this).attr('data-nav');
                clearTimeout(qcloud[_nav + '_timer']);
                qcloud[_nav + '_timer'] = setTimeout(function () {
                    $('[data-nav]').each(function () {
                        $(this)[_nav == $(this).attr('data-nav') ? 'addClass' : 'removeClass']('nav-up-selected');
                    });
                    $('#' + _nav).stop(true, true).slideDown(300);
                }, 150);
            }, function () {
                var _nav = $(this).attr('data-nav');
                clearTimeout(qcloud[_nav + '_timer']);
                qcloud[_nav + '_timer'] = setTimeout(function () {
                    $('[data-nav]').removeClass('nav-up-selected');
                    $('#' + _nav).stop(true, true).slideUp(300);
                }, 150);
            });
        });
