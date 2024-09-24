import Link from 'next/link'

function Page() {
    return (
        <>
            <Head>
                <title>Create Next App</title>
                <link rel="icon" href="/favicon.ico"/>
            </Head>
            <h1>
                Read
                <Link href="/post/first-post">
                    this page!
                </Link>
            </h1>
        </>
    )
}

export default Page;