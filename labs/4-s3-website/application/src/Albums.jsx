import React, {useEffect, useState} from 'react';
import axios from 'axios';
import ImageUploader from "react-images-upload";

const Album = ({id, name, images}) => (<div key={id} className={"album"}>
    <h2>{name}</h2>
    <div className={"images"}>
        {images.map((img, index) => (
            <img key={index} src={img.url} alt={"No idea what it is about"} width="400"/>))}
    </div>
    <UploadImage albumId={id}/>
</div>)

export const Albums = () => {
    const [albums, setAlbums] = useState([]);
    useEffect(() => {
        async function getAlbums() {
            const {data} = await axios.get(`${process.env.REACT_APP_BACKEND_URL}/albums`)
            setAlbums(data);
        }

        getAlbums();
    }, [])
    return (<div className={"albums"}>{albums.map(album => (
        <Album key={album.id} id={album.id} name={album.name} images={album.images}/>))}</div>);
}

const UploadImage = ({albumId}, props) => {
    const [pictures, setPictures] = useState([]);

    const onDrop = async picture => {
        setPictures([...pictures, picture]);
        const image = await uploadImageToBackend(picture[0]);
        await addToAlbum(albumId, image.url);
    };
    return (
        <ImageUploader
            {...props}
            withIcon={false}
            withLabel={false}
            buttonText={"Upload image"}
            onChange={onDrop}
            imgExtension={[".jpg", ".gif", ".png", ".gif"]}
            maxFileSize={5242880}
        />
    );
}

const uploadImageToBackend = async (picture) => {
    const formData = new FormData();
    formData.append('file', picture)
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }
    const { data } = await axios.post(`${process.env.REACT_APP_BACKEND_URL}/image/_upload`, formData, config);
    return data;
}

const addToAlbum = async (albumId, imageUrl) => {
    console.log(`Adding ${imageUrl} to ${albumId}`);
    await axios.put(`${process.env.REACT_APP_BACKEND_URL}/albums/${albumId}`, {url: imageUrl});
}
