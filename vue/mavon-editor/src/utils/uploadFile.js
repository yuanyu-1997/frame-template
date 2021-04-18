export const uploadFile = file => {
  const param = new FormData();
  param.append("file", file.file);

  const config = {
    headers: { "Content-Type": "multipart/form-data" }
  };
  axios.post("/upload", param, config).then(res => {
    console.log(res);
  });
};
