import { useEffect, useState } from "react";
import { Table, Button, Modal, Form, Input, message } from "antd";
import employeeApi from "../../api/employeeApi";

export default function EmployeePage() {
  const [data, setData] = useState([]);
  const [open, setOpen] = useState(false);
  const [editing, setEditing] = useState(null);
  const [form] = Form.useForm();

  const load = async () => {
    const res = await employeeApi.getAll();
    setData(res.data);
  };

  useEffect(() => {
    load();
  }, []);

  // 🔥 mở modal
  const handleOpen = (record = null) => {
    setEditing(record);
    setOpen(true);

    if (record) {
      form.setFieldsValue({
        ...record,
        password: "", // không fill password
      });
    } else {
      form.resetFields();
    }
  };

  // 🔥 submit
  const submit = async () => {
    try {
      const values = await form.validateFields();

      if (editing) {
        await employeeApi.update(editing.id, values);
        message.success("Cập nhật thành công");
      } else {
        await employeeApi.create(values);
        message.success("Thêm thành công");
      }

      setOpen(false);
      setEditing(null);
      form.resetFields();
      load();
    } catch (err) {
      message.error("Lỗi!");
    }
  };

  const columns = [
    { title: "Tên", dataIndex: "ten" },
    { title: "Username", dataIndex: "username" },
    { title: "Email", dataIndex: "email" },
    { title: "SĐT", dataIndex: "soDienThoai" },
    { title: "Giới tính", dataIndex: "gioiTinh" },
    {
      title: "Action",
      render: (_, r) => (
        <Button onClick={() => handleOpen(r)}>Sửa</Button>
      ),
    },
  ];

  return (
    <div style={{ padding: 20 }}>
      <h2>👨‍💼 Quản lý nhân viên</h2>

      <Button type="primary" onClick={() => handleOpen()}>
        + Thêm nhân viên
      </Button>

      <Table
        rowKey="id"
        dataSource={data}
        columns={columns}
        style={{ marginTop: 20 }}
      />

      <Modal
        title={editing ? "Cập nhật nhân viên" : "Thêm nhân viên"}
        open={open}
        onOk={submit}
        onCancel={() => setOpen(false)}
      >
        <Form form={form} layout="vertical">
          <Form.Item
            name="ten"
            label="Tên"
            rules={[{ required: true, message: "Không được để trống" }]}
          >
            <Input />
          </Form.Item>

          <Form.Item
            name="username"
            label="Username"
            rules={[{ required: true }]}
          >
            <Input disabled={!!editing} />
          </Form.Item>

          <Form.Item
            name="password"
            label="Password"
            rules={[
              {
                required: !editing,
                message: "Không được để trống",
              },
              { min: 6, message: "Ít nhất 6 ký tự" },
            ]}
          >
            <Input.Password placeholder="Để trống nếu không đổi" />
          </Form.Item>

          <Form.Item
            name="email"
            label="Email"
            rules={[
              { required: true, message: "Không được để trống" },
              { type: "email", message: "Email không hợp lệ" },
            ]}
          >
            <Input />
          </Form.Item>

          <Form.Item name="soDienThoai" label="SĐT">
            <Input />
          </Form.Item>

          <Form.Item name="gioiTinh" label="Giới tính">
            <Input />
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
}