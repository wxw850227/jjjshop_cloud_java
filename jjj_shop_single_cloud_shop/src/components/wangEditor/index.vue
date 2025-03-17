<template>
	<div>
		<div v-show="!monacoBox">
			<div ref="wang-editor" class="wang-editor" />
		</div>
		<Upload v-if="isupload" :config="{ total: 9 }" :isupload="isupload" @returnImgs="returnImgsFunc">上传图片</Upload>
	</div>
</template>

<script>
	import E from "wangeditor";
	import Upload from "@/components/file/Upload.vue";
	export default {
		name: "Index",
		components: {
			Upload,
		},
		props: {
			content: {
				type: String,
				default: "",
			},
		},

		data() {
			return {
				monacoBox: false,
				value: "",
				modalPic: false,
				isChoice: "多选",
				picTit: "danFrom",
				img: "",
				modalVideo: false,
				editor: null,
				uploadSize: 2,
				video: "",
				isupload: false,
			};
		},

		watch: {
			content(val) {
				if (this.editor && this.editor.txt.html() !== val) {
					this.editor.txt.html(val);
				}
			},
		},

		mounted() {
			this.createEditor();
		},

		methods: {
			openUpload(callback) {
				this.isupload = true;
				this.callback = callback;
				console.log(callback, "callback");
			},
			// 当 Upload 组件返回图片后调用
			returnImgsFunc(imgs) {
				console.log(imgs, "imgs");
				if (imgs && this.callback) {
					imgs.forEach((img) => {
						if (img.filePath) {
							this.callback(img.filePath);
						}
					});
				}
				this.isupload = false;
			},
			changeValue(value) {
				this.newHtml = value;
				console.log(this.newHtml);
				this.$emit("editorContent", value);
				this.$emit("input", value);
			},
			// 插入本地图片到编辑器
			insertImage(imgUrl) {
				console.log(imgUrl, "imgUrl1111");
				this.editor.cmd.do(
					"insertHTML",
					`<img src="${imgUrl}" style="max-width:100%;"/>`
				);
			},

			// 本地视频选择
			handleVideoChange(event) {
				const file = event.target.files[0];
				if (file && file.type.startsWith("video/")) {
					const reader = new FileReader();
					reader.onload = () => {
						const videoUrl = reader.result;
						this.insertVideo(videoUrl);
					};
					reader.readAsDataURL(file);
				}
			},

			// 插入视频到编辑器
			insertVideo(videoUrl) {
				const videoHTML =
					`<video src="${videoUrl}" controls style="max-width:100%; min-height:500rpx;"></video><p><br></p>`;
				this.editor.cmd.do("insertHTML", videoHTML);
			},

			createEditor() {
				let _this = this;
				this.editor = new E(this.$refs["wang-editor"]);

				this.editor.config.menus = [
					"alertHtml",
					"head",
					"bold",
					"fontSize",
					"fontName",
					"italic",
					"underline",
					"strikeThrough",
					"indent",
					"lineHeight",
					"foreColor",
					"backColor",
					"link",
					"list",
					"justify",
					"quote",
					"emoticon",
					"image", // 取消默认的图片上传方式
					// "video", // 添加视频上传
					"code",
					"splitLine",
				];
				this.editor.config.uploadImgFromMedia = function() {
					_this.openUpload((imgUrl) => {
						_this.insertImage(imgUrl);
					});
				};
				this.editor.config.uploadImgShowBase64 = false;
				this.editor.config.zIndex = 0;

				this.editor.config.onchange = (newHtml) => {
					this.newHtml = newHtml;
					this.$emit("contens", newHtml);
				};

				this.editor.create();
				this.editor.txt.html(this.content);
			},
		},
	};
</script>

<style lang="scss" scoped>
	.bottom {
		margin-bottom: 10px;
		cursor: pointer;
	}

	.monaco-box :deep(.el-textarea__inner) {
		height: 600px;
	}

	:deep(.w-e-toolbar) {
		z-index: 2 !important;
	}
</style>