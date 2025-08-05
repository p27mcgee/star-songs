yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title('Star Songs')
        link(rel: 'stylesheet', type: 'text/css', href: '/css/bootstrap.min.css')
        script(src: '/js/bootstrap.min.js', '')
    }
    body {
        h3(class: 'text-center', 'Star Songs')
        div(class: 'row justify-content-center') {
            div(class: 'col-auto') {
                table(class: 'table table-responsive') {
                    tr {
                        th('Song')
                        th('Artist')
                    }
                  songs.each { song ->
                    tr {
                        td() {
                            a(href:"$song.url", target: '_blank', rel: 'noopener noreferrer',
                                "$song.title")
                        }
                        td("${artistMapById[song.artistId].name}")
                    }
                  }
                }
            }
        }
    }
}
